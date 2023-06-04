package io.github.seujorgenochurras.manager;

import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.file.DependencyFileNotFoundException;
import io.github.seujorgenochurras.file.FileSearcher;
import io.github.seujorgenochurras.mapper.DependencyMapper;

import java.io.File;

public class DependencyManager {
   public static final DependencyManagerFile dependencyManagerFile;
   private static final File dependencyManagerRootFile;

   private DependencyManager() {
   }

   static {
      dependencyManagerRootFile = getDependencyManagerAsFile();
      dependencyManagerFile = DependencyMapper.mapFile(dependencyManagerRootFile);
   }


   private static File getDependencyManagerAsFile() {
      return FileSearcher
              .searchForFile("build.gradle.kts")
              .ifNotFoundSearchFor("build.gradle")
              .ifNotFoundSearchFor("maven.pom")
              .ifNotFoundThrow(() -> new DependencyFileNotFoundException("No dependency manager file found"))
              .getSearchResult();
   }
}
