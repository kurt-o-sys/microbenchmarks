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
