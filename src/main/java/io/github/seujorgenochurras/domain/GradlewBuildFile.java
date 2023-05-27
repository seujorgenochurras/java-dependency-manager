package io.github.seujorgenochurras.domain;

import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GradlewBuildFile implements DependencyManagerFile {

   private GradlePlugins plugins;
   private GradleDependencies dependencies;

   private File originFile;

   private String originFileAsString;

   private FileWriter fileWriter;


   public GradleDependencies getDependencies() {
      return dependencies;
   }

   public GradlePlugins getPlugins() {
      return plugins;
   }

   public GradlewBuildFile setPlugins(GradlePlugins plugins) {
      this.plugins = plugins;
      return this;
   }

   public File getOriginFile() {
      return originFile;
   }

   public GradlewBuildFile setOriginFile(File originFile) {
      this.originFile = originFile;
      this.originFileAsString = FileUtils.getFileAsString(originFile);
      return this;
   }

   public FileWriter getFileWriter() {
      if (this.fileWriter == null) {
         this.fileWriter = tryInstantiateFileWriterFromFile(originFile);
      }
      return fileWriter;
   }

   private FileWriter tryInstantiateFileWriterFromFile(File file) {
      try {
         return new FileWriter(file);
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   public GradlewBuildFile setDependencies(GradleDependencies dependencies) {
      this.dependencies = dependencies;
      return this;
   }

   @Override
   public void addDependency(GradleDependency gradleDependency) {
      //TODO add dependencyType

      String declaration = "\n implementation(\""
              + gradleDependency.getGroupName()
              + ":"
              + gradleDependency.getArtifact()
              + ":"
              + gradleDependency.getVersion()
              + "\")";


      addTextToOriginFile(declaration, getIndexOfInsideDependenciesBlock());
      tryRewriteOriginFile();
   }

   private void addTextToOriginFile(String text, int whereToAdd) {
      String secondOriginFileHalf = text + originFileAsString.substring(whereToAdd);
      originFileAsString = originFileAsString.substring(0, whereToAdd) + secondOriginFileHalf;
   }

   private void tryRewriteOriginFile() {
      try {
         getFileWriter().write(originFileAsString);
         getFileWriter().close();
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   private int getIndexOfInsideDependenciesBlock() {
      int dependenciesStringLength = "dependencies {".length();
      return FileUtils.getFileAsString(originFile).trim().lastIndexOf("dependencies {") + dependenciesStringLength;
   }

   @Override
   public String toString() {
      return "GradlewBuildFile{" +
              "plugins=" + plugins +
              ", dependencies=" + dependencies +
              '}';
   }
}
