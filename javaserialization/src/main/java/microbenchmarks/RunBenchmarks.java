package microbenchmarks;

import org.openjdk.jmh.runner.*;

/**
 * Created by kurt on 22/06/14.
 */
public class RunBenchmarks {

   public static void main(String[] args) throws RunnerException {
      //new Runner(new BenchmarkOptionsBuilder().getOptions(1)).run();
      //new Runner(new BenchmarkOptionsBuilder().getOptions(2)).run();
      //new Runner(new BenchmarkOptionsBuilder().getOptions(4)).run();
      new Runner(new BenchmarkOptionsBuilder().getOptions(8)).run();
   }
}
