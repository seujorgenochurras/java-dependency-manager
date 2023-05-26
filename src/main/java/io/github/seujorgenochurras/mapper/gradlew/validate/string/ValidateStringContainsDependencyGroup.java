package io.github.seujorgenochurras.mapper.gradlew.validate.string;

import io.github.seujorgenochurras.mapper.gradlew.validate.GradlewMapperValidator;

public class ValidateStringContainsDependencyGroup implements GradlewMapperValidator<String> {
   @Override
   public boolean validate(String thingToValidate) {
      return false;
   }
}
