package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.domain.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.gradlew.GradleGroovyMapper;
import io.github.seujorgenochurras.mapper.gradlew.GradleKotlinMapper;
import io.github.seujorgenochurras.mapper.maven.MavenPomMapper;

import java.io.File;
import java.util.NoSuchElementException;

public abstract class DependencyMapper {

   private final File rootFile;

   protected DependencyMapper(File rootFile) {
      this.rootFile = rootFile;
   }

   public static DependencyManagerFile mapFile(File file) {
      return getDependencyTypeFromFile(file).map();
   }
   private static DependencyMapper getDependencyTypeFromFile(File file){
      String fileName = file.getName();
      switch (fileName){
         case "build.gradle.kts" -> {
            return new GradleKotlinMapper(file);
         }
         case "build.gradle" -> {
            return new GradleGroovyMapper(file);
         }
         case "maven.pom" -> {
            return new MavenPomMapper(file);
         }

         default -> throw new NoSuchElementException("Dependency file not found");
      }
   }


   protected abstract DependencyManagerFile map();
   protected abstract void mapDependencies();
   protected abstract void mapPlugins();

}
