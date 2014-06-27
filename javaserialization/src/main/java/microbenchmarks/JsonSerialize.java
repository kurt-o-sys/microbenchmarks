package microbenchmarks;

import com.fasterxml.jackson.core.*;
import microbenchmarks.model.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

public class JsonSerialize
      extends BenchmarkSerialize
{

   public JsonSerialize() {
      super(JsonSerialize.class);

   }

   private JsonSerialize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private FactoidJson factoid
         = new FactoidJson(entity, attribute, value);


   @Benchmark
   public byte[] benchmarkSerialize() {
      try
      {
         return JsonMapper.mapper().writeValueAsBytes(factoid);
      } catch (JsonProcessingException e)
      {
         e.printStackTrace();
      }

      return new byte[]{};

   }


   public static void main(String[] args) throws RunnerException {
      BenchmarkSerialize bs = new JsonSerialize(JsonSerialize.class);
      bs.runBenchmark();
   }

}
