package io.github.seujorgenochurras.mapper.gradlew.clean;

import java.util.Objects;

public class GradlewLine {
   private String lineAsString;

   private GradlewLineComment comment;

   public GradlewLine(String lineAsString) {
      this.lineAsString = lineAsString;

      if (stringContainsComment(lineAsString)){
         comment = new GradlewLineComment(lineAsString);
      }
   }

   private boolean stringContainsComment(String string){
      return string.contains("//") || string.contains("/*") || string.contains("*/");
   }
   public boolean containsComment() {
      return !Objects.isNull(comment);
   }

   public GradlewLineComment getComment() {
      return comment;
   }

   public String getAsString() {
      return lineAsString;
   }

   public GradlewLine setLine(String lineAsString) {
      this.lineAsString = lineAsString;
      return this;
   }
}
