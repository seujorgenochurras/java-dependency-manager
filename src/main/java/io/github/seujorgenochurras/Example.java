package io.github.seujorgenochurras;

import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.dependency.DependencyType;
import io.github.seujorgenochurras.manager.DependencyManager;


public class Example {
   public static void main(String[] args)  {
     DependencyManagerFile dependencyManagerFile = DependencyManager.dependencyManagerFile;
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
