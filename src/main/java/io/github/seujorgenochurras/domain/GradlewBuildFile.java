package io.github.seujorgenochurras.domain;

public class GradlewBuildFile implements DependencyManagerFile{

   private GradlePlugins plugins;
   private GradleDependencies dependencies;


   public GradleDependencies getDependencies() {
      return dependencies;
   }

   public GradlePlugins getPlugins() {
      return plugins;
   }

   public GradlewBuildFile setPlugins(GradlePlugins plugins) {
      this.plugins = plugins;
      return this;
   }

   public GradlewBuildFile setDependencies(GradleDependencies dependencies) {
      this.dependencies = dependencies;
      return this;
   }

   @Override
   public void addDependency(GradleDependency gradleDependency) {

   }

   @Override
   public String toString() {
      return "GradlewBuildFile{" +
              "plugins=" + plugins +
              ", dependencies=" + dependencies +
              '}';
   }
}
