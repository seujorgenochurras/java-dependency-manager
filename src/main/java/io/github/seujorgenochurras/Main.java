package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.GradleDependency;
import io.github.seujorgenochurras.domain.GradlewBuildFile;
import io.github.seujorgenochurras.mapper.gradlew.mapper.GradlewFileMapper;

import java.io.File;

public class Main {

   public static void main(String[] args) {
      File gradlewFile = new File("build.gradle.kts");
      GradlewBuildFile buildFile = GradlewFileMapper.mapFile(gradlewFile);

      buildFile.addDependency(new GradleDependency()
              .setGroupName("com.google.code.gson")
              .setArtifact("gson")
              .setVersion("2.10.1"));


   }
}