package microbenchmarks;

import org.openjdk.jmh.annotations.*;

import java.time.*;

@State(Scope.Thread)
public class ObjectCreationBenchmarks {

   private final long entity = 2L;

   private final long longAttribute = 3L;

   private final String stringAttribute = "an attribute";

   private final String value = "a value of an attribute of an entity";

   @Benchmark
   public FactoidLongConstructor longWithConstructor() {
      return new FactoidLongConstructor(entity, longAttribute, value);
   }

   @Benchmark
   public FactoidLongSetters longWithSetters() {
      FactoidLongSetters result = new FactoidLongSetters();
      result.setAttribute(longAttribute);
      result.setEntity(entity);
      result.setTimestamp(Instant.now());
      result.setValue(value);
      return result;
   }

   @Benchmark
   public FactoidLongBuilder longWithBuilder() {
      return FactoidLongBuilder.createNew().entity(entity).attribute(longAttribute).hasValue(value).create();
   }

   @Benchmark
   public FactoidStringConstructor stringWithConstructor() {
      return new FactoidStringConstructor(entity, stringAttribute, value);
   }

   @Benchmark
   public FactoidStringSetters stringWithSetters() {
      FactoidStringSetters result = new FactoidStringSetters();
      result.setAttribute(stringAttribute);
      result.setEntity(entity);
      result.setTimestamp(Instant.now());
      result.setValue(value);
      return result;
   }

   @Benchmark
   public FactoidStringBuilder stringWithBuilder() {
      return FactoidStringBuilder.createNew().entity(entity).attribute(stringAttribute).hasValue(value).create();
   }
}
