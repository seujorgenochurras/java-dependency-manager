package io.github.seujorgenochurras.mapper.gradlew.validate;

public interface GradlewMapperValidator<T> {
      boolean validate(T thingToValidate);

}
