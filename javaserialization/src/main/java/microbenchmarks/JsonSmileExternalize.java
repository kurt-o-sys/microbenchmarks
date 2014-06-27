package microbenchmarks;

import microbenchmarks.model.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;

/**
 * Created by kurt on 22/06/14.
 */
public class JsonSmileExternalize extends BenchmarkSerialize {

   public JsonSmileExternalize() {
      super(JsonSmileExternalize.class);

   }

   private JsonSmileExternalize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private FactoidExtJsonSmile factoid
         = new FactoidExtJsonSmile(entity, attribute, value);


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
      BenchmarkSerialize bs = new JsonSmileExternalize(JsonSmileExternalize.class);
      bs.runBenchmark();
   }

}
