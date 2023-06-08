package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.dependency.DependencyType;
import io.github.seujorgenochurras.manager.DependencyManager;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;


public class Example {
   public static void main(String[] args)  {
     DependencyManagerFile dependencyManagerFile = DependencyManager.getDependencyManagerFile();
     Dependency dependency = DependencyBuilder.startBuild()
             .group("awiodjaw")
             .artifact("ajwodijawoda")
             .version("1.1.1.1")
             .dependencyType(DependencyType.IMPLEMENTATION)
             .buildResult();
     dependencyManagerFile.addDependency(dependency);
     dependencyManagerFile.commentDependency(dependency);
   }
}
