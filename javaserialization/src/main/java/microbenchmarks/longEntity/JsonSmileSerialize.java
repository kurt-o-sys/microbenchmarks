package microbenchmarks.longEntity;

import com.fasterxml.jackson.core.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

/**
 * Created by qsys on 22/06/14.
 */
public class JsonSmileSerialize
      extends BenchmarkSerialize
{

   public JsonSmileSerialize() {
      super(JsonSmileSerialize.class);

   }

   private JsonSmileSerialize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private microbenchmarks.longEntity.model.FactoidJson factoid
         = new microbenchmarks.longEntity.model.FactoidJson(entity, attribute, value);


   @Benchmark
   public byte[] benchmarkSerialize() {
      try
      {
         return microbenchmarks.longEntity.model.JsonMapper.smileMapper().writeValueAsBytes(factoid);
      } catch (JsonProcessingException e)
      {
         e.printStackTrace();
      }

      return new byte[]{};

   }


   public static void main(String[] args) throws RunnerException {
      BenchmarkSerialize bs = new JsonSmileSerialize(JsonSmileSerialize.class);
      bs.runBenchmark();
   }

}
