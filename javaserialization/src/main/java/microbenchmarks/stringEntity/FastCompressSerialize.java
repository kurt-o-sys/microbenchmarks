package microbenchmarks.stringEntity;

import de.ruedigermoeller.serialization.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */
public class FastCompressSerialize
      extends BenchmarkSerialize
{

   private FSTConfiguration fstConf = FSTConfiguration.createDefaultConfiguration();

   private microbenchmarks.stringEntity.model.FactoidFSTCompressed factoid
         = new microbenchmarks.stringEntity.model.FactoidFSTCompressed(entity, attribute, value);


   public FastCompressSerialize() {
      super(FastCompressSerialize.class);

   }

   private FastCompressSerialize(Class benchmarkClass) {
      super(benchmarkClass);
   }

   @Setup(Level.Iteration)
   public void prepare() {
      fstConf.registerClass(microbenchmarks.stringEntity.model.FactoidFSTCompressed.class, Instant.class);
   }

   @Benchmark
   public byte[] benchmarkSerialize() {
      ByteArrayOutputStream localOutputStream = new ByteArrayOutputStream();
      FSTObjectOutput fstOut = fstConf.getObjectOutput(localOutputStream);
      try
      {
         fstOut.writeObject(factoid, microbenchmarks.stringEntity.model.FactoidFSTCompressed.class);
         fstOut.flush();
         localOutputStream.close();
      } catch (IOException e)
      {
         e.printStackTrace();
      }
      return localOutputStream.toByteArray();

   }


   public static void main(String[] args) throws RunnerException {
      BenchmarkSerialize bs = new FastCompressSerialize(FastCompressSerialize.class);
      bs.runBenchmark();
   }

}
