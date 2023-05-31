package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.Dependency;
import io.github.seujorgenochurras.domain.DependencyType;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;

import java.io.File;

public class Main {
      private static DependencyManagerFile buildFile;
   public static void main(String[] args) {
      buildFile = DependencyMapper.mapFile(new File("src/main/java/io/github/seujorgenochurras/build.gradle"));
      Dependency gsonDependency = new Dependency();
      gsonDependency
              .setArtifact("gson")
              .setGroupName("com.google")
              .setVersion("1.2.3")
              .setDependencyType(DependencyType.RUNTIME_ONLY);
      System.out.println(buildFile.getDependencies());
      System.out.println(buildFile.getPlugins());
   }

}
