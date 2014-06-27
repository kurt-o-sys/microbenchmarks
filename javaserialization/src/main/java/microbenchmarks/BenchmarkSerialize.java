package microbenchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;

@State(Scope.Benchmark)
public abstract class BenchmarkSerialize {

   protected long entity = 23571117L;

   protected long attribute = 19232931L;

   protected String value = "some value of some attribute of some entity of some factoid";

   private final Class<? extends BenchmarkSerialize> benchmarkClass;

   BenchmarkSerialize(Class<? extends BenchmarkSerialize> benchmarkClass) {
      this.benchmarkClass = benchmarkClass;
   }


   public void analyzeObject() {
      byte[] array = new byte[0];
      try
      {
         array = benchmarkClass.newInstance().benchmarkSerialize();
      } catch (InstantiationException e)
      {
         e.printStackTrace();
      } catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }
      System.out.println(array.length);
      try
      {
         System.out.println(new String(array, "UTF-8"));
      } catch (UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }
   }

   public void runBenchmark() {
      this.analyzeObject();
      System.out.println("--------------------------------");
      try
      {
         new Runner(new BenchmarkOptionsBuilder().getOptions(benchmarkClass)).run();
      } catch (RunnerException e)
      {
         e.printStackTrace();
      }

   }

   public abstract byte[] benchmarkSerialize();

}
