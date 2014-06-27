package microbenchmarks;

import org.openjdk.jmh.runner.*;

/**
 * Created by kurt on 22/06/14.
 */
public class RunBenchmarks {

   public static void main(String[] args) throws RunnerException {
      new Runner(new BenchmarkOptionsBuilder().getOptions()).run();
   }
}
