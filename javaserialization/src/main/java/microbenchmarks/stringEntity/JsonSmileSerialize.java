package microbenchmarks.stringEntity;

import com.fasterxml.jackson.core.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

/**
 * Created by kurt on 22/06/14.
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

   private microbenchmarks.stringEntity.model.FactoidJson factoid
         = new microbenchmarks.stringEntity.model.FactoidJson(entity, attribute, value);


   @Benchmark
   public byte[] benchmarkSerialize() {
      try
      {
         return microbenchmarks.stringEntity.model.JsonMapper.smileMapper().writeValueAsBytes(factoid);
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
