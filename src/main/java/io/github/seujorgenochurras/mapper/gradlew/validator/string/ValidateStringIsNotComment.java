package io.github.seujorgenochurras.mapper.gradlew.validator.string;

import io.github.seujorgenochurras.mapper.gradlew.validator.GradlewMapperValidator;

public class ValidateStringIsNotComment implements GradlewMapperValidator<String> {
   @Override
   public boolean validate(String thingToValidate) {
      return false;
   }
}
