package microbenchmarks.longEntity;

import com.fasterxml.jackson.core.*;
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

   private microbenchmarks.longEntity.model.FactoidJson factoid
         = new microbenchmarks.longEntity.model.FactoidJson(entity, attribute, value);


   @Benchmark
   public byte[] benchmarkSerialize() {
      try
      {
         return microbenchmarks.longEntity.model.JsonMapper.mapper().writeValueAsBytes(factoid);
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
