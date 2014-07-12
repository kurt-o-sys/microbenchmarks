package microbenchmarks.stringEntity.model;

import java.io.*;
import java.time.*;

/**
 * Created by qsys on 22/06/14.
 */

public class FactoidExtJsonSmile
      implements Externalizable
{

   protected long entity;

   protected String attribute;

   protected String value;

   protected Instant timestamp;


   public FactoidExtJsonSmile(long entity, String attribute, String value) {
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
      JsonMapper.smileMapper().writeValue(new ExternalizableOutput(out), this);

   }

   @Override
   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      JsonMapper.smileMapper().readerForUpdating(this).readValue(new ExternalizableInput(in));
   }
}
