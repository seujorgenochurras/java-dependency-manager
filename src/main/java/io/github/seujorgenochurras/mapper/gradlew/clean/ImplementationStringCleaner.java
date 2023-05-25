package io.github.seujorgenochurras.mapper.gradlew.clean;

public class ImplementationStringCleaner {
   private final String junkyImplementationsString;

   private final String cleanerImplementationsString = "";

 private ImplementationStringCleaner(String junkyImplementationsString) {
      this.junkyImplementationsString = junkyImplementationsString;
   }

   public static ImplementationStringCleaner startCleaning(String junkyImplementationsString){
      return new ImplementationStringCleaner(junkyImplementationsString);
   }

   public ImplementationStringCleaner removeComments(){


    return this;
   }


}
