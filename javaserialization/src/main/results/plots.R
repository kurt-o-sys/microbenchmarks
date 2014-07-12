library("rjson")

results<-data.frame()
setwd("/home/kurt/dev/microbenchmarks/javaserialization/src/main/results")

for (t in c(1,2,4)) {

jsondata<-fromJSON(file=paste("results_",t,"t.json", sep=""))

benchmarks<-c()
for (i in 1:length(jsondata)) {
  benchmarks<-c(benchmarks, jsondata[[i]]$benchmark)
}

sizes<-data.frame(benchmarks)
sizes<-cbind(sizes, size=c(83,89,267,189,125,176,107,226,166,205,140,288,94,100,294,211,133,200,117,247,174,228,150,210))

rm(benchmarks)

for (i in 1:length(jsondata)) {
  item<-jsondata[[i]]
  benchmark<-factor(item$benchmark)
  mode<-factor(item$mode)
  entity<-factor(ifelse(grepl("longEntity",item$benchmark), "long","string"))
  threads<-item$threads
  smile<-ifelse(grepl("[sS]mile",item$benchmark), TRUE,FALSE)
  array<-ifelse(grepl("[aA]rray",item$benchmark), TRUE,FALSE)
  json<-ifelse(grepl("[jJ]son",item$benchmark), TRUE,FALSE)
  ext<-ifelse(grepl("[eE]xt",item$benchmark), TRUE,FALSE)
  fast<-ifelse(grepl("[fF]ast",item$benchmark), TRUE,FALSE)
  compress<-ifelse(grepl("[cC]ompress",item$benchmark), TRUE,FALSE)
  size<-sizes[sizes[,"benchmarks"]==toString(benchmark),]$size
  
  for (fork in 1:length(item$primaryMetric$rawData)) {
    for(it in 1:length(item$primaryMetric$rawData[[fork]])) {
      row<-data.frame(
        benchmark=benchmark,
        mode=mode,
        entity=entity,
        threads=threads,
        fork=fork,
        smile=smile,
        array=array,
        json=json,
        ext=ext,
        fast=fast,
        compress=compress,
        iteration=it,
        metric=item$primaryMetric$rawData[[fork]][it],
        size=size
      )
      results <- rbind(results, row)
    }
  }
}
rm(sizes,array,row,benchmark,mode,compress,entity,ext,fast,fork,i,it,item,json,size,smile,threads)
}


avgt<-results[results$mode=="avgt" & results$threads==1,]
model<-metric~entity+fork+smile+array+json+ext+fast+compress+iteration+smile:array

lm<-lm(model, data=avgt)
lmbest<-stepAIC(lm, direction=c("both"))


library(MASS)
step<-1
lambda<-seq(0,10,step)
for (i in 1:10) {
  lmridge<-lm.ridge(model, data=avgt, lambda=lambda)
  valuemin<-lambda[which.min(unname(lmridge$GCV))]
  lambda<-seq(valuemin-step, valuemin+step,step/5)
  step<-step/5
}
lmridge<-lm.ridge(model, data=avgt, lambda=valuemin)



library(lars)
lars<-lars(cbind(avgt$entity,avgt$fork,avgt$smile,avgt$array,avgt$json,avgt$ext,avgt$fast,avgt$compress,avgt$iteration),avgt$metric, type=c("lar"), intercept=T)
bestfit<-lars$Cp[which.min(unname(lars$Cp))]
lmlars<-coef(lars)[bestfit,]
names(lmlars)<-c("entity","fork","smile","array","json","ext","fast","compress","iteration")

plotmode="avgt"
plotylabel=expression(paste("avgt(",mu,"s)"))
plotentity="long"

avgt<-results[results$mode==plotmode,]

avgt.longx<-with (avgt[avgt$entity==plotentity,], tapply(size,benchmark,mean))
avgt.longy<-with (avgt[avgt$entity==plotentity,], tapply(metric,benchmark,mean))

levels<-unique(avgt[avgt$entity==plotentity,]$benchmark)
avgt.labels<-paste(ifelse(grepl("[jJ]son",levels), "json",""),
                   ifelse(grepl("[aA]rray",levels), "[]",""),
                   ifelse(grepl("[sS]mile",levels), ":)"," "),
                   ifelse(grepl("[eE]xt",levels), "ext ","ser "),
                   ifelse(grepl("[fF]ast",levels), "fst",""),
                   ifelse(grepl("[cC]ompress",levels), "-c",""),
                   sep="")
sd<-unname(with (avgt[avgt$entity==plotentity,], tapply(metric,benchmark,sd)))
#sd<-sd[!is.na(sd)]
plot(avgt.longy~avgt.longx,
     xlab="size (kb)", ylab=plotylabel,
     xlim=c(60, 300), ylim=c(0.5, 2.85),
     cex=0.5)
text(avgt.longx, avgt.longy, avgt.labels, cex=0.62, 
     adj=c(-0.1,-0.6))
arrows(avgt.longx, avgt.longy-sd, avgt.longx, avgt.longy+sd, code=0)
