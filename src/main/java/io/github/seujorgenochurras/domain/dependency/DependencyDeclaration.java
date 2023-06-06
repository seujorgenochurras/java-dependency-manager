package io.github.seujorgenochurras.domain.dependency;

public class DependencyDeclaration {
   private String rawDeclaration;
   private int declarationLine;

   private Dependency dependency;

   public DependencyDeclaration(String rawDeclaration, int declarationLine) {
      this.rawDeclaration = rawDeclaration;
      this.declarationLine = declarationLine;
   }

   public String getRawDeclaration() {
      return rawDeclaration;
   }

   public DependencyDeclaration setRawDeclaration(String rawDeclaration) {
      this.rawDeclaration = rawDeclaration;
      return this;
   }

   public int getDeclarationLine() {
      return declarationLine;
   }

   public DependencyDeclaration setDeclarationLine(int declarationLine) {
      this.declarationLine = declarationLine;
      return this;
   }

   public Dependency toDependencyObject() {
      if (dependency != null) return dependency;

      String cleanDeclaration = rawDeclaration.replaceAll("[(\")]", " ")
              .replaceAll(" {2}", "");

      String dependencyType = cleanDeclaration.split(" ")[0];
      cleanDeclaration = cleanDeclaration.replaceFirst("^(.*?) ", "");

      String[] dependencyDeclarationComponents = cleanDeclaration.split(":");
      String dependencyGroup = dependencyDeclarationComponents[0];
      String dependencyArtifact = dependencyDeclarationComponents[1];
      String dependencyVersion = dependencyDeclarationComponents.length == 3 ? dependencyDeclarationComponents[2] : "";

      dependency = DependencyBuilder.startBuild()
              .group(dependencyGroup)
              .artifact(dependencyArtifact)
              .version(dependencyVersion)
              .dependencyType(DependencyType.valueOf(dependencyType))
              .buildResult();

      return dependency;
   }
}
