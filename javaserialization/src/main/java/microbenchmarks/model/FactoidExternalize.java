package microbenchmarks.model;

import java.io.*;
import java.time.*;

/**
 * Created by kurt on 22/06/14.
 */
public final class FactoidExternalize
      implements Externalizable
{

   protected long entity;

   protected long attribute;

   protected String value;

   protected Instant timestamp;

   public FactoidExternalize(long entity, long attribute, String value) {
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


   @Override
   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeLong(entity);
      out.writeLong(attribute);
      out.writeObject(value);
      out.writeObject(timestamp);

   }

   @Override
   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      entity = in.readLong();
      attribute = in.readLong();
      value = (String) in.readObject();
      timestamp = (Instant) in.readObject();
   }
}
