package io.github.seujorgenochurras.domain.manager;

import io.github.seujorgenochurras.domain.Dependency;
import io.github.seujorgenochurras.domain.Plugin;

import java.io.File;
import java.util.List;

public class GradleBuildFileBuilder {
   private GradleBuildFileBuilder(){}

   private final GradleBuildFile gradleBuildFile = new GradleBuildFile();

   public static GradleBuildFileBuilder startBuild(){
      return new GradleBuildFileBuilder();
   }
   public GradleBuildFileBuilder dependencies(List<Dependency> dependencyList){
      this.gradleBuildFile.setDependencies(dependencyList);
      return this;
   }
   public GradleBuildFileBuilder plugins(List<Plugin> plugins){
      this.gradleBuildFile.setPlugins(plugins);
      return this;
   }
   public GradleBuildFileBuilder originFile(File file ){
      this.gradleBuildFile.setOriginFile(file);
      return this;
   }
   public GradleBuildFile getBuild(){
      return this.gradleBuildFile;
   }

}
