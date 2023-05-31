package io.github.seujorgenochurras.mapper.gradlew.mapper;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import org.junit.jupiter.api.Test;

import java.io.File;

 class GradleMapperTest {
   private static final DependencyManagerFile buildFile;
   static {
      buildFile = DependencyMapper.mapFile(new File("build.gradle.kts"));
   }

   @Test
   void isMappingDependencies(){
      assertNotNull(buildFile.getDependencies());
      assertNotEquals(0, buildFile.getDependencies().size());
   }
   @Test
   void isMappingPlugins(){
      assertNotNull(buildFile.getDependencies());
      assertNotEquals(0, buildFile.getPlugins().size());
   }


}
