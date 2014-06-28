package microbenchmarks.longEntity.model;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */
public class FactoidSerialize
      implements Serializable
{

   protected long entity;

   protected long attribute;

   protected String value;

   protected Instant timestamp;


   public FactoidSerialize(long entity, long attribute, String value) {
      this.entity = entity;
      this.attribute = attribute;
      this.value = value;
      this.timestamp = Instant.now();
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
