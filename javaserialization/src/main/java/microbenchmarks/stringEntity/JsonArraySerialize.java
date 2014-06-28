package microbenchmarks.stringEntity;

import com.fasterxml.jackson.core.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

/**
 * Created by kurt on 22/06/14.
 */
public class JsonArraySerialize
      extends BenchmarkSerialize
{

   public JsonArraySerialize() {
      super(JsonArraySerialize.class);

   }

   private JsonArraySerialize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   private microbenchmarks.stringEntity.model.FactoidJsonArray factoid
         = new microbenchmarks.stringEntity.model.FactoidJsonArray(entity, attribute, value);


   @Benchmark
   public byte[] benchmarkSerialize() {
      try
      {
         return microbenchmarks.stringEntity.model.JsonMapper.mapper().writeValueAsBytes(factoid);
      } catch (JsonProcessingException e)
      {
         e.printStackTrace();
      }

      return new byte[]{};

   }


   public static void main(String[] args) throws RunnerException {
      BenchmarkSerialize bs = new JsonArraySerialize(JsonArraySerialize.class);
      bs.runBenchmark();
   }

}
