package microbenchmarks.stringEntity;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;

/**
 * Created by kurt on 22/06/14.
 */
public class JsonExternalize
      extends BenchmarkSerialize
{

   public JsonExternalize() {
      super(JsonExternalize.class);

   }

   private JsonExternalize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private microbenchmarks.stringEntity.model.FactoidExtJson factoid
         = new microbenchmarks.stringEntity.model.FactoidExtJson(entity, attribute, value);


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
      BenchmarkSerialize bs = new JsonExternalize(JsonExternalize.class);
      bs.runBenchmark();
   }

}
