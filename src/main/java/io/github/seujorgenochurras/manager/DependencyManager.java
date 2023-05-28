package io.github.seujorgenochurras.manager;

import io.github.seujorgenochurras.domain.DependencyManagerFile;
import io.github.seujorgenochurras.domain.GradleDependency;
import io.github.seujorgenochurras.file.DependencyFileNotFoundException;
import io.github.seujorgenochurras.file.FileSearcher;

import java.io.File;

public class DependencyManager {
   private static final DependencyManagerFile dependencyManagerFile;
   private static final File dependencyManagerRootFile;

   private DependencyManager(){}

   static {
      dependencyManagerRootFile = getDependencyManagerAsFile();
      dependencyManagerFile = DependencyFileMapper.mapFile(dependencyManagerRootFile);
   }

   public static void addDependency(GradleDependency dependency){
      dependencyManagerFile.addDependency(dependency);
   }

   private static File getDependencyManagerAsFile(){
      return FileSearcher
              .searchForFile("build.gradle.kts")
              .ifNotFoundSearchFor("build.gradle")
              .ifNotFoundSearchFor("maven.pom")
              .ifNotFoundThrow(() -> new DependencyFileNotFoundException("No dependency manager file found"))
              .getSearchResult();
   }
}
