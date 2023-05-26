package io.github.seujorgenochurras.domain;

import java.util.ArrayList;
import java.util.List;

public class GradleDependencies {
   private List<GradleDependency> dependencies = new ArrayList<>();

   public void addDependency(GradleDependency gradleDependency){
      dependencies.add(gradleDependency);
   }

   public List<GradleDependency> getDependencies() {
      return dependencies;
   }

   public GradleDependencies setDependencies(List<GradleDependency> dependencies) {
      this.dependencies = dependencies;
      return this;
   }

   @Override
   public String toString() {
      return "GradleDependencies{" +
              "dependencies=" + dependencies +
              '}';
   }
}
