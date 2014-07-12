package microbenchmarks.longEntity;

import microbenchmarks.longEntity.model.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;

/**
 * Created by qsys on 22/06/14.
 */
public class JsonArraySmileExternalize
      extends BenchmarkSerialize
{

   public JsonArraySmileExternalize() {
      super(JsonArraySmileExternalize.class);

   }

   private JsonArraySmileExternalize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private FactoidExtJsonArraySmile factoid
         = new FactoidExtJsonArraySmile(entity, attribute, value);


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
      BenchmarkSerialize bs = new JsonArraySmileExternalize(JsonArraySmileExternalize.class);
      bs.runBenchmark();
   }

}
