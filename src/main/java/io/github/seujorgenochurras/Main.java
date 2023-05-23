package io.github.seujorgenochurras;

import io.github.seujorgenochurras.mapper.GradlewFileMapper;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
   public static void main(String[] args) throws FileNotFoundException {
      GradlewFileMapper gradlewFileMapper = new GradlewFileMapper();
      File gradlewFile = new File("build.gradle.kts");
      gradlewFileMapper.mapFile(gradlewFile);
   }
}