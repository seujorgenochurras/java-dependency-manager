package io.github.seujorgenochurras.file;

public enum SearchPlaces {
   PROJECT_ROOT("../../../");

   public final String placePath;
   SearchPlaces(String placePath) {
      this.placePath = placePath;
   }
}
