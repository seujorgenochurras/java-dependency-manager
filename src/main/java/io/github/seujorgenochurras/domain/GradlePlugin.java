package io.github.seujorgenochurras.domain;

public class GradlePlugin {
   private String idName;

   public String getIdName() {
      return idName;
   }

   public GradlePlugin setIdName(String idName) {
      this.idName = idName;
      return this;
   }

   public GradlePlugin(String idName) {
      this.idName = idName;
   }

   @Override
   public String toString() {
      return "GradlePlugin{" +
              "idName=" + idName +
              '}';
   }
}
