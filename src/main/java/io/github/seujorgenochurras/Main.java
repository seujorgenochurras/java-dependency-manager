package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.DependencyType;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.mapper.DependencyMapper;

import java.io.File;

public class Main {
   public static void main(String[] args)  {
      DependencyMapper.mapFile(new File("build.gradle.kts"))
              .addDependency(DependencyBuilder
                      .startBuild()
                      .version("2")
                      .group("awdwa")
                      .artifact("awsd")
                      .buildResult());

   }
}
