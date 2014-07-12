package microbenchmarks;

import java.io.*;
import java.time.*;

/**
 * Created by qsys on 22/06/14.
 */
public class FactoidLongBuilder
      implements Serializable
{

   protected long entity;

   protected long attribute;

   protected String value;

   protected Instant timestamp;

   interface EntityStep {

      AttributeStep entity(long entity);
   }

   interface AttributeStep {

      ValueStep attribute(long attribute);
   }

   interface ValueStep {

      BuildStep hasValue(String value);
   }

   interface BuildStep {

      FactoidLongBuilder create();
   }

   private static final class StepBuilder
         implements BuildStep, ValueStep, AttributeStep, EntityStep
   {

      protected long entity;

      protected long attribute;

      protected String value;

      @Override
      public ValueStep attribute(long attribute) {
         this.attribute = attribute;
         return this;
      }

      @Override
      public FactoidLongBuilder create() {
         return new FactoidLongBuilder(this);
      }

      @Override
      public AttributeStep entity(long entity) {
         this.entity = entity;
         return this;
      }

      @Override
      public BuildStep hasValue(String value) {
         this.value = value;
         return this;
      }
   }


   private FactoidLongBuilder(StepBuilder builder) {
      this.entity = builder.entity;
      this.attribute = builder.attribute;
      this.value = builder.value;
      this.timestamp = Instant.now();
   }

   public static EntityStep createNew() {
      return new StepBuilder();
   }


   public long getEntity() {
      return entity;
   }

   public long getAttribute() {
      return attribute;
   }

   public String getValue() {
      return value;
   }

   public Instant getTimestamp() {
      return timestamp;
   }
}
