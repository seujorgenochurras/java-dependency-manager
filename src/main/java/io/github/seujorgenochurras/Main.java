package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.Dependency;
import io.github.seujorgenochurras.domain.DependencyType;

public class Main {
   public static void main(String[] args) {
      Dependency gsonDependency = new Dependency();
      gsonDependency
              .setArtifact("gson")
              .setGroupName("com.google")
              .setVersion("1.2.3")
              .setDependencyType(DependencyType.RUNTIME_ONLY);
   }
}
