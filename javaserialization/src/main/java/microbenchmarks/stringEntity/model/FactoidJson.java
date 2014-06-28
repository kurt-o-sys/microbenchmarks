package microbenchmarks.stringEntity.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat.*;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */
@JsonFormat(shape = Shape.OBJECT)
public final class FactoidJson
      implements Serializable
{

   protected long entity;

   protected String attribute;

   protected String value;

   protected Instant timestamp;


   public FactoidJson(long entity, String attribute, String value) {
      this.entity = entity;
      this.attribute = attribute;
      this.value = value;
      timestamp = Instant.now();
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
