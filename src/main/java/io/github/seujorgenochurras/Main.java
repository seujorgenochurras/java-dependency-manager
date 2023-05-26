package io.github.seujorgenochurras;



import io.github.seujorgenochurras.mapper.gradlew.mapper.GradlewFileMapper;

import java.io.File;

public class Main {

   public static void main(String[] args) {
      File gradlewFile = new File("build.gradle.kts");
      System.out.println(GradlewFileMapper.mapFile(gradlewFile));
   }
}