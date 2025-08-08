package com.originrealms.enhanced.core.util.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PatternTypeAdapter extends TypeAdapter<Pattern> {
   public static PatternTypeAdapter INSTANCE = new PatternTypeAdapter();

   public void write(JsonWriter writer, Pattern pattern) throws IOException {
      writer.value(pattern == null ? null : pattern.pattern());
   }

   public Pattern read(JsonReader reader) throws IOException {
      return Pattern.compile(reader.nextString());
   }
}
