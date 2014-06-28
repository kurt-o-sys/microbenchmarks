package microbenchmarks.stringEntity.model;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */

public final class FactoidExtJson
      implements Externalizable
{

   protected long entity;

   protected String attribute;

   protected String value;

   protected Instant timestamp;


   public FactoidExtJson(long entity, String attribute, String value) {
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


   @Override
   public void writeExternal(ObjectOutput out) throws IOException {
      JsonMapper.mapper().writeValue(new ExternalizableOutput(out), this);

   }

   @Override
   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      JsonMapper.mapper().readerForUpdating(this).readValue(new ExternalizableInput(in));
   }
}
