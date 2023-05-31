package io.github.seujorgenochurras.domain;

public class Dependency {
   private String groupName;
   private String artifact;
   private String version;

   private DependencyType dependencyType;
   public Dependency() {
   }


   public DependencyType getDependencyType() {
      return dependencyType;
   }

   public Dependency setDependencyType(DependencyType dependencyType) {
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

   public Dependency setGroupName(String groupName) {
      this.groupName = groupName;
      return this;
   }

   public Dependency setArtifact(String artifact) {
      this.artifact = artifact;
      return this;
   }

   public Dependency setVersion(String version) {
      this.version = version;
      return this;
   }

   @Override
   public String toString() {
      return "Dependency{" +
              "groupName='" + groupName + '\'' +
              ", artifact='" + artifact + '\'' +
              ", version='" + version + '\'' +
              '}';
   }
}

