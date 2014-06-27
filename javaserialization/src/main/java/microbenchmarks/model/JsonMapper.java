package microbenchmarks.model;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.*;

/**
 * Created by kurt on 22/06/14.
 */
public final class JsonMapper {

   private final ObjectMapper defaultMapper = new ObjectMapper();

   private final ObjectMapper smileMapper = new ObjectMapper(new SmileFactory());


   private static class LazyHolder {

      private static final JsonMapper INSTANCE = new JsonMapper();
   }

   private static JsonMapper instance() {
      return LazyHolder.INSTANCE;
   }

   private JsonMapper() {

   }

   public static ObjectMapper mapper() { return LazyHolder.INSTANCE.defaultMapper; }

   public static ObjectMapper smileMapper() { return LazyHolder.INSTANCE.smileMapper; }

}
