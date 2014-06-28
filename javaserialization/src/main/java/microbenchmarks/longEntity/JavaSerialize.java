package microbenchmarks.longEntity;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;

public class JavaSerialize
      extends BenchmarkSerialize
{

   public JavaSerialize() {
      super(JavaSerialize.class);

   }

   private JavaSerialize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private microbenchmarks.longEntity.model.FactoidSerialize factoid
         = new microbenchmarks.longEntity.model.FactoidSerialize(entity, attribute, value);


   @Benchmark
   public byte[] benchmarkSerialize() {
      ByteArrayOutputStream localOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream localObjectOutputStream = null;
      try
      {
         localObjectOutputStream = new ObjectOutputStream(localOutputStream);
         localObjectOutputStream.writeObject(factoid);
         localObjectOutputStream.close();
         localOutputStream.close();
      } catch (IOException e)
      {
         e.printStackTrace();
      }
      return localOutputStream.toByteArray();

   }


   public static void main(String[] args) throws RunnerException {
      BenchmarkSerialize bs = new JavaSerialize(JavaSerialize.class);
      bs.runBenchmark();
   }

}
