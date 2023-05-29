package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.domain.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;

import java.io.File;

public class GradleGroovyMapper extends DependencyMapper {
   public GradleGroovyMapper(File rootFile) {
      super(rootFile);
   }

   @Override
   protected DependencyManagerFile map() {

      return null;
   }

   @Override
   protected void mapDependencies() {

   }

   @Override
   protected void mapPlugins() {

   }
}
