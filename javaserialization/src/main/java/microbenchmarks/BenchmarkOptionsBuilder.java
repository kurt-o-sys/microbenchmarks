package microbenchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.*;
import org.openjdk.jmh.runner.options.*;

import java.util.concurrent.*;

/**
 * Created by kurt on 22/06/14.
 */
public class BenchmarkOptionsBuilder {

   public BenchmarkOptionsBuilder() {
   }

   public Options getOptions(Class benchmarkClass) {
      return new OptionsBuilder()
            .include(".*" + benchmarkClass.getSimpleName() + ".*")
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.MICROSECONDS)
            .warmupIterations(3)
            .measurementIterations(3)
            .forks(2)
            .build();
   }

   public Options getOptions(int threads) {
      return new OptionsBuilder()
            .include(".*")
            .mode(Mode.All)
            .timeUnit(TimeUnit.MICROSECONDS)
            .warmupIterations(10)
            .measurementIterations(10)
            .forks(4)
            .threads(threads)
            .resultFormat(ResultFormatType.JSON)
            .result("results_" + threads + "t.json")
            .output(null)
            .build();
   }
}
