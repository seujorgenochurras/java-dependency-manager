package io.github.seujorgenochurras.mapper.maven;

import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;

import java.io.File;

public class MavenPomMapper extends DependencyMapper {
   public MavenPomMapper(File rootFile) {
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
