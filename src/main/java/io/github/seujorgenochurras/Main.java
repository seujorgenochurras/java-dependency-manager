package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.DependencyManagerFile;
import io.github.seujorgenochurras.domain.DependencyType;
import io.github.seujorgenochurras.domain.GradleDependency;
import io.github.seujorgenochurras.manager.DependencyManager;

public class Main {
   public static void main(String[] args) {
      GradleDependency gsonDependency = new GradleDependency();
      gsonDependency
              .setArtifact("gson")
              .setGroupName("com.google")
              .setVersion("1.2.3")
              .setDependencyType(DependencyType.RUNTIME_ONLY);


      DependencyManagerFile dependencyManagerFile = DependencyManager.getDependencyManagerFile();
      dependencyManagerFile.addDependency(gsonDependency);
   }
}
