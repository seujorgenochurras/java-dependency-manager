package io.github.seujorgenochurras.domain.manager.maven;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;

import java.util.List;

public class MavenBuildFile implements DependencyManagerFile {
   @Override
   public void addDependency(Dependency dependency) {

   }

   @Override
   public <T extends AbstractPlugin> void addPlugin(T plugin) {

   }

   @Override
   public List<? extends AbstractPlugin> getPlugins() {
      return null;
   }

   @Override
   public List<Dependency> getDependencies() {
      return null;
   }

   @Override
   public void removeDependency(Dependency dependency) {

   }

   @Override
   public void commentDependency(Dependency dependency) {

   }

   @Override
   public <T extends AbstractPlugin> void removePlugin(T plugin) {

   }
}
