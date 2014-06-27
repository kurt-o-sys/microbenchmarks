package microbenchmarks;

import microbenchmarks.model.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;

public class ObjectExternalize extends BenchmarkSerialize {

   public ObjectExternalize() {
      super(ObjectExternalize.class);

   }

   private ObjectExternalize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private FactoidExternalize factoid
         = new FactoidExternalize(entity, attribute, value);


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
      BenchmarkSerialize bs = new ObjectExternalize(ObjectExternalize.class);
      bs.runBenchmark();
   }

}
