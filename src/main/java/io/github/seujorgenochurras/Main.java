package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.manager.DependencyManager;


public class Main {
   public static void main(String[] args)  {
      DependencyManager.dependencyManagerFile
              .addDependency(DependencyBuilder
                      .startBuild()
                      .version("2")
                      .group("awdwa")
                      .artifact("awsd")
                      .buildResult());

   }
}
