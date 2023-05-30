package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.mapper.DependencyManagerFile;

import java.io.File;

public class GradleKotlinMapper extends Gradle {


   public GradleKotlinMapper(File rootFile) {
      super(rootFile);
   }

   @Override
   protected DependencyManagerFile map() {

      return this;
   }

   @Override
   protected void mapDependencies() {

   }

   @Override
   protected void mapPlugins() {

   }



}
