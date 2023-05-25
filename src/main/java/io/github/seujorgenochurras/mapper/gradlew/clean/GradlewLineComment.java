package io.github.seujorgenochurras.mapper.gradlew.clean;

public class GradlewLineComment {
   private String commentedLine;

   public GradlewLineComment(String commentedLine) {
      this.commentedLine = commentedLine;
   }

   public boolean isCommentInLineComment() {
      //if you have an inline comment inside a multiline comment it doesnt counts
      return commentedLine.indexOf("/*") < commentedLine.indexOf("//");
   }

   public String removeComment() {
      if (isCommentInLineComment() || isCommentMultiLineInitializer()) {
         commentedLine = commentedLine.substring(0, getIndexOfComment());
      } else {
         commentedLine = commentedLine.substring(getIndexOfComment());
      }
      return commentedLine;
   }
   public boolean isMultiLineComment(){
      return commentedLine.contains("/*") || commentedLine.contains("*/");
   }
   public boolean isCommentMultiLineInitializer() {
      return commentedLine.indexOf("//") < commentedLine.indexOf("/*");
   }

   private int getIndexOfComment() {
      if (!commentedLine.contains("//")) {
         if (!commentedLine.contains("/*")) {
            return commentedLine.indexOf("*/");
         } else {
            return commentedLine.indexOf("/*");
         }
      }
      return commentedLine.indexOf("//");
   }
}
