package io.github.seujorgenochurras.mapper.gradlew.validate;

import java.util.ArrayList;

public class GradleValidatorChain<T> {

   private final ArrayList<GradlewMapperValidator<T>> validators = new ArrayList<>();

   private GradleValidatorChain() {
   }

   public static <T> GradleValidatorChain<T> startValidationChainOf(Class<T> typeOfThingToValidate) {
      return new GradleValidatorChain<>();
   }

   public GradleValidatorChain<T> addValidator(GradlewMapperValidator<T> gradlewMapperValidator) {
      validators.add(gradlewMapperValidator);
      return this;
   }

   public boolean validate(T thingToValidate) {

      for (GradlewMapperValidator<T> validator : validators) {
         if (validationDidNotPass(validator, thingToValidate)) {
            return false;
         }
      }
         return true;
   }
   private boolean validationDidNotPass(GradlewMapperValidator<T> gradlewMapperValidator, T thingToValidate){
      return !gradlewMapperValidator.validate(thingToValidate);
   }
}
