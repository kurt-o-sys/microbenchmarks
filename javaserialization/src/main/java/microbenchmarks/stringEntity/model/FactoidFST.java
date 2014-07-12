package microbenchmarks.stringEntity.model;

import de.ruedigermoeller.serialization.annotations.*;

import java.io.*;
import java.time.*;

/**
 * Created by qsys on 22/06/14.
 */
@Flat
@Predict(FactoidFST.class)
public final class FactoidFST
      implements Serializable
{

   protected long entity;

   protected String attribute;

   @Flat
   protected String value;

   @Predict(Instant.class)
   protected Instant timestamp;


   public FactoidFST(long entity, String attribute, String value) {
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

