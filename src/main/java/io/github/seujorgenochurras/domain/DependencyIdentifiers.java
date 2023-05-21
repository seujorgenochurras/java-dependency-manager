package io.github.seujorgenochurras.domain;

public class DependencyIdentifiers {
   private String groupName;
   private String artifact;
   private String version;

   private DependencyType dependencyType;

   private DependencyIdentifiers() {
   }

   public String getGroupName() {
      return groupName;
   }

   private void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String getArtifact() {
      return artifact;
   }

   private void setArtifact(String artifact) {
      this.artifact = artifact;
   }

   public String getVersion() {
      return version;
   }

   private void setVersion(String version) {
      this.version = version;
   }

   public static final class DependencyIdentifiersBuilder {
      private final DependencyIdentifiers buildResult = new DependencyIdentifiers();

      private DependencyIdentifiersBuilder() {
      }

      public static DependencyIdentifiersBuilder startBuild() {
         return new DependencyIdentifiersBuilder();
      }

      public DependencyIdentifiersBuilder groupName(String groupName) {
         buildResult.setGroupName(groupName);
         return this;
      }

      public DependencyIdentifiersBuilder artifactName(String artifactName) {
         buildResult.setArtifact(artifactName);
         return this;
      }

      public DependencyIdentifiersBuilder version(String version) {
         buildResult.setVersion(version);
         return this;
      }

      public DependencyIdentifiers getBuildResult() {
         return buildResult;
      }
   }
}

