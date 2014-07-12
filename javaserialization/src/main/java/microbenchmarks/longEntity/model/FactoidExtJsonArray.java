package microbenchmarks.longEntity.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat.*;

import java.io.*;
import java.time.*;

/**
 * Created by qsys on 22/06/14.
 */
@JsonFormat(shape = Shape.ARRAY)
@JsonPropertyOrder(alphabetic = true)
public class FactoidExtJsonArray
      implements Externalizable
{

   protected long entity;

   protected long attribute;

   protected String value;

   protected Instant timestamp;


   public FactoidExtJsonArray(long entity, long attribute, String value) {
      this.entity = entity;
      this.attribute = attribute;
      this.value = value;
      timestamp = Instant.now();
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

   @Override
   public void writeExternal(ObjectOutput out) throws IOException {
      JsonMapper.mapper().writeValue(new ExternalizableOutput(out), this);

   }

   @Override
   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      JsonMapper.mapper().readerForUpdating(this).readValue(new ExternalizableInput(in));
   }
}
