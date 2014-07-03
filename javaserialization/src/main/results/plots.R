library("rjson")

setwd("/home/kurt/dev/microbenchmarks/javaserialization/src/main/results")
jsondata<-fromJSON(file="results1.json")
results<-data.frame()

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
rm(sizes,array,row,benchmark,compress,entity,ext,fast,fork,i,it,item,json,size,smile,threads)



library(Hmisc)

errbar(benchmark$size, benchmark$"average time(us/op)", 
       benchmark$"average time(us/op)"+1.96*benchmark$"err average time",
       benchmark$"average time(us/op)"-1.96*benchmark$"err average time",
       xlab="size (bytes)", ylab="average time (us/op)",
       xlim=c(50,275), ylim=c(0.4,2.25)) 
text(benchmark$size, benchmark$"average time(us/op)", 
     labels = row.names(benchmark), pos = 3, cex=0.62)

errbar(benchmark$size, benchmark$"single shot time (us)", 
       benchmark$"single shot time (us)"+benchmark$"err single shot time",
       benchmark$"single shot time (us)"-benchmark$"err single shot time",
       xlab="size (bytes)", ylab="single shot time (us) +/- sd",
       xlim=c(50,275)) 
text(benchmark$size, benchmark$"single shot time (us)",
     labels = row.names(benchmark), pos = 3, cex=0.62)
