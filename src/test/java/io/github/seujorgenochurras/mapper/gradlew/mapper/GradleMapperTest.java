package io.github.seujorgenochurras.mapper.gradlew.mapper;


import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

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

   @Test
   void isAddingDependency(){
      Dependency dependency = DependencyBuilder.startBuild()
              .group("test.test")
              .artifact("testImpl")
              .version("69.42.0")
              .buildResult();
      buildFile.addDependency(dependency);

      assertTrue(buildFile.getDependencies().contains(dependency));
   }

   private <T> boolean listContains(List<T> list, T object){
      if(object == null)return false;

      AtomicBoolean listContainsElement = new AtomicBoolean(false);
      list.forEach(element -> {
            if(!listContainsElement.get()) listContainsElement.set(object.equals(element));
      });
      return listContainsElement.get();
   }


}
