package microbenchmarks;

import com.fasterxml.jackson.core.*;
import microbenchmarks.model.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

/**
 * Created by kurt on 22/06/14.
 */
public class JsonArraySmileSerialize
      extends BenchmarkSerialize
{

   public JsonArraySmileSerialize() {
      super(JsonArraySmileSerialize.class);

   }

   private JsonArraySmileSerialize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private FactoidJsonArray factoid
         = new FactoidJsonArray(entity, attribute, value);


   @Benchmark
   public byte[] benchmarkSerialize() {
      try
      {
         return JsonMapper.smileMapper().writeValueAsBytes(factoid);
      } catch (JsonProcessingException e)
      {
         e.printStackTrace();
      }

      return new byte[]{};

   }


   public static void main(String[] args) throws RunnerException {
      BenchmarkSerialize bs = new JsonArraySmileSerialize(JsonArraySmileSerialize.class);
      bs.runBenchmark();
   }

}
