package io.github.seujorgenochurras.domain.dependency;

import io.github.seujorgenochurras.domain.DependencyType;

public class Dependency {
   private String groupName;
   private String artifact;
   private String version;

   private DependencyType dependencyType;

    Dependency() {
      //only by builder
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Dependency that)) return false;

      if (getGroupName() != null ? !getGroupName().equals(that.getGroupName()) : that.getGroupName() != null)
         return false;
      if (getArtifact() != null ? !getArtifact().equals(that.getArtifact()) : that.getArtifact() != null) return false;
      if (getVersion() != null ? !getVersion().equals(that.getVersion()) : that.getVersion() != null) return false;
      return getDependencyType() == that.getDependencyType();
   }

   @Override
   public int hashCode() {
      int result = getGroupName() != null ? getGroupName().hashCode() : 0;
      result = 31 * result + (getArtifact() != null ? getArtifact().hashCode() : 0);
      result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
      result = 31 * result + (getDependencyType() != null ? getDependencyType().hashCode() : 0);
      return result;
   }
}

