package microbenchmarks;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */
public class FactoidStringConstructor
      implements Serializable
{

   protected long entity;

   protected String attribute;

   protected String value;

   protected Instant timestamp;


   public FactoidStringConstructor(long entity, String attribute, String value) {
      this.entity = entity;
      this.attribute = attribute;
      this.value = value;
      this.timestamp = Instant.now();
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
