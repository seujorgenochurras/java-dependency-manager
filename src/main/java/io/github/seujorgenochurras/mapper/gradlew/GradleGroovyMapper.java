package io.github.seujorgenochurras.mapper.gradlew;


import io.github.seujorgenochurras.mapper.DependencyManagerFile;

import java.io.File;

public class GradleGroovyMapper extends Gradle {
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
