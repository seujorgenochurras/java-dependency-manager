package io.github.seujorgenochurras.domain.manager;

import io.github.seujorgenochurras.domain.Dependency;
import io.github.seujorgenochurras.domain.Plugin;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GradleBuildFile implements DependencyManagerFile {
   private List<Dependency> dependencies;
   private List<Plugin> plugins;
   private File originFile;
   private String originFileAsString;
   private FileWriter fileWriter;


   @Override
   public List<Dependency> getDependencies() {
      return dependencies;
   }

   @Override
   public List<Plugin> getPlugins() {
      return plugins;
   }

   public GradleBuildFile setPlugins(List<Plugin> plugins) {
      this.plugins = plugins;
      return this;
   }

   public File getOriginFile() {
      return originFile;
   }

    GradleBuildFile setOriginFile(File originFile) {
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

   public GradleBuildFile setDependencies(List<Dependency> dependencies) {
      this.dependencies = dependencies;
      return this;
   }

   @Override
   public void addDependency(Dependency gradleDependency) {
      //TODO add dependencyType

      String declaration = "\n implementation \""
              + gradleDependency.getGroupName().trim()
              + ":"
              + gradleDependency.getArtifact().trim()
              + ":"
              + gradleDependency.getVersion().trim()
              + "\"";


      addTextToOriginFile(declaration, getIndexOfBlock("dependencies"));
      tryRewriteOriginFile();
   }

   @Override
   public void addPlugin(Plugin plugin) {
      String declaration = "\n id '" + plugin.getId().trim() + "'";

      addTextToOriginFile(declaration, getIndexOfBlock("plugins"));
      tryRewriteOriginFile();
   }

   private void addTextToOriginFile(String text, int indexOfWhereToWrite) {
      String secondOriginFileHalf = text + originFileAsString.substring(indexOfWhereToWrite);
      originFileAsString = originFileAsString.substring(0, indexOfWhereToWrite) + secondOriginFileHalf;
   }

   private void tryRewriteOriginFile() {
      try {
         getFileWriter().write(originFileAsString);
         getFileWriter().close();
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   private int getIndexOfBlock(String blockName){
      int dependenciesStringLength = blockName.length();
      return FileUtils.getFileAsString(originFile).lastIndexOf(blockName) + dependenciesStringLength;
   }


   @Override
   public String toString() {
      return "GradleBuildFile{" +
              "plugins=" + plugins +
              ", dependencies=" + dependencies +
              '}';
   }
}
