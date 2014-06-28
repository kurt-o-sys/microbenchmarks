package microbenchmarks.longEntity.model;

import java.io.*;

public final class ExternalizableOutput extends OutputStream {

   private final ObjectOutput out;

   public ExternalizableOutput(ObjectOutput out) {
      this.out = out;
   }

   @Override
   public void flush() throws IOException {
      out.flush();
   }

   @Override
   public void close() throws IOException {
      out.close();
   }

   @Override
   public void write(int ch) throws IOException {
      out.write(ch);
   }

   @Override
   public void write(byte[] data) throws IOException {
      out.write(data);
   }

   @Override
   public void write(byte[] data, int offset, int len) throws IOException {
      out.write(data, offset, len);
   }
}

