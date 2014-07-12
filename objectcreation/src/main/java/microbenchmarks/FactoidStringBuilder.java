package microbenchmarks;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */
public class FactoidStringBuilder
      implements Serializable
{

   protected long entity;

   protected String attribute;

   protected String value;

   protected Instant timestamp;

   interface EntityStep {

      AttributeStep entity(long entity);
   }

   interface AttributeStep {

      ValueStep attribute(String attribute);
   }

   interface ValueStep {

      BuildStep hasValue(String value);
   }

   interface BuildStep {

      FactoidStringBuilder create();
   }

   private static final class StepBuilder
         implements BuildStep, ValueStep, AttributeStep, EntityStep
   {

      protected long entity;

      protected String attribute;

      protected String value;

      @Override
      public ValueStep attribute(String attribute) {
         this.attribute = attribute;
         return this;
      }

      @Override
      public FactoidStringBuilder create() {
         return new FactoidStringBuilder(this);
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


   private FactoidStringBuilder(StepBuilder builder) {
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

   public String getAttribute() {
      return attribute;
   }

   public String getValue() {
      return value;
   }

   public Instant getTimestamp() {
      return timestamp;
   }
}
