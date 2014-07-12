package microbenchmarks;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */
public class FactoidLongSetters
      implements Serializable
{

   protected long entity;

   protected long attribute;

   protected String value;

   protected Instant timestamp;


   public FactoidLongSetters() {
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

   public void setEntity(long entity) {
      this.entity = entity;
   }

   public void setAttribute(long attribute) {
      this.attribute = attribute;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public void setTimestamp(Instant timestamp) {
      this.timestamp = timestamp;
   }
}
