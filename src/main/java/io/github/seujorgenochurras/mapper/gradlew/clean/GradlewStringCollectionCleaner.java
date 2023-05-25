package io.github.seujorgenochurras.mapper.gradlew.clean;


import java.util.*;
import java.util.stream.Collectors;

public class GradlewStringCollectionCleaner {
   private List<String> gradlewBuildFileLines;

   private GradlewStringCollectionCleaner(List<String> gradlewBuildFileLines) {
      this.gradlewBuildFileLines = gradlewBuildFileLines;
   }

   public static GradlewStringCollectionCleaner startCleaning(List<String> stringListToBeClean) {
      return new GradlewStringCollectionCleaner(stringListToBeClean);
   }

   public GradlewStringCollectionCleaner removeComments() {
      System.out.println("BEGFORE " + gradlewBuildFileLines);
      List<String> lines = new ArrayList<>();

      boolean isInsideMultiLineComment = false;
      for (String lineAsString : gradlewBuildFileLines) {
         GradlewLine line = new GradlewLine(lineAsString);
         if (line.containsComment()) {
            GradlewLineComment comment = line.getComment();

            if (comment.isMultiLineComment()) {
               isInsideMultiLineComment = comment.isCommentMultiLineInitializer();
            }
            line.setLine(comment.removeComment());
         }

         if (!isInsideMultiLineComment) {
            lines.add(line.getAsString());
         }
      }
      System.out.println("\n\n\n\nn\nAFTER " + lines);
      this.gradlewBuildFileLines = lines;
      return this;
   }

   public GradlewStringCollectionCleaner trim(){
         gradlewBuildFileLines = gradlewBuildFileLines.stream().map(String::trim).toList();
      return this;
   }
   public List<String> getCleanList(){
      return gradlewBuildFileLines;
   }
}
