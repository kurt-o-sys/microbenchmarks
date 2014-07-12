package microbenchmarks.longEntity;

import com.fasterxml.jackson.core.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

/**
 * Created by qsys on 22/06/14.
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

   private microbenchmarks.longEntity.model.FactoidJsonArray factoid
         = new microbenchmarks.longEntity.model.FactoidJsonArray(entity, attribute, value);


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
      BenchmarkSerialize bs = new JsonArraySerialize(JsonArraySerialize.class);
      bs.runBenchmark();
   }

}
