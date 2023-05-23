package io.github.seujorgenochurras.mapper.gradlew.validator;

public interface GradlewMapperValidator<T> {
      boolean validate(T thingToValidate);

}
