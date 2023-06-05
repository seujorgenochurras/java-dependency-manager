package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.manager.DependencyManager;


public class Example {
   public static void main(String[] args) {
      DependencyManager.getDependencyManagerFile(System.getProperty("user.dir"))
              .addDependency(DependencyBuilder
                      .startBuild()
                      .version("2asdasdas")
                      .group("awdasd1231231asdwa")
                      .artifact("awasdas132123sd")
                      .buildResult());
   }
}
