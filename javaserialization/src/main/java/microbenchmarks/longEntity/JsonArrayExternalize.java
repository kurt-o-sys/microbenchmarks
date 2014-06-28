package microbenchmarks.longEntity;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;

/**
 * Created by kurt on 22/06/14.
 */
public class JsonArrayExternalize
      extends BenchmarkSerialize
{

   public JsonArrayExternalize() {
      super(JsonArrayExternalize.class);

   }

   private JsonArrayExternalize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private microbenchmarks.longEntity.model.FactoidExtJsonArray factoid
         = new microbenchmarks.longEntity.model.FactoidExtJsonArray(entity, attribute, value);


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
      BenchmarkSerialize bs = new JsonArrayExternalize(JsonArrayExternalize.class);
      bs.runBenchmark();
   }

}
