package io.github.seujorgenochurras.domain;

public class GradleDependency {
   private String groupName;
   private String artifact;
   private String version;

   private DependencyType dependencyType;
   public GradleDependency() {
   }


   public DependencyType getDependencyType() {
      return dependencyType;
   }

   public GradleDependency setDependencyType(DependencyType dependencyType) {
      this.dependencyType = dependencyType;
      return this;
   }

   public String getGroupName() {
      return groupName;
   }


   public String getArtifact() {
      return artifact;
   }


   public String getVersion() {
      return version;
   }

   public GradleDependency setGroupName(String groupName) {
      this.groupName = groupName;
      return this;
   }

   public GradleDependency setArtifact(String artifact) {
      this.artifact = artifact;
      return this;
   }

   public GradleDependency setVersion(String version) {
      this.version = version;
      return this;
   }

   @Override
   public String toString() {
      return "GradleDependency{" +
              "groupName='" + groupName + '\'' +
              ", artifact='" + artifact + '\'' +
              ", version='" + version + '\'' +
              '}';
   }
}

