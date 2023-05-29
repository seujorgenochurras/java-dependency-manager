package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.DependencyType;
import io.github.seujorgenochurras.domain.GradleDependency;

public class Main {
   public static void main(String[] args) {
      GradleDependency gsonDependency = new GradleDependency();
      gsonDependency
              .setArtifact("gson")
              .setGroupName("com.google")
              .setVersion("1.2.3")
              .setDependencyType(DependencyType.RUNTIME_ONLY);
   }
}
