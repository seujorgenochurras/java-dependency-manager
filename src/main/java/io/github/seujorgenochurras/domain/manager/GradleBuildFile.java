package io.github.seujorgenochurras.domain.manager;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradleBuildFile implements DependencyManagerFile {
   private List<Dependency> dependencies;
   private List< AbstractPlugin> plugins;
   private File originFile;
   private String originFileAsString;
   private FileWriter fileWriter;


   @Override
   public List<Dependency> getDependencies() {
      return dependencies;
   }

   @Override
   public List<? extends AbstractPlugin> getPlugins() {
      return plugins;
   }

   public void setPlugins(List<AbstractPlugin> plugins) {
      this.plugins = plugins;
   }

   public void setDependencies(List<Dependency> dependencies) {
      this.dependencies = dependencies;
   }

   public File getOriginFile() {
      return originFile;
   }

   public void setOriginFile(File originFile) {
      this.originFile = originFile;
      this.originFileAsString = FileUtils.getFileAsString(originFile);
    }

   public FileWriter getFileWriter() {
      if (this.fileWriter == null) {
         this.fileWriter = tryInstantiateFileWriterFromFile(originFile);
      }
      return fileWriter;
   }


   @Override
   public void addDependency(Dependency dependency) {
      String declaration = "\n" + dependency.getDependencyType().typeName + " (\""
              + dependency.getGroupName().trim()
              + ":"
              + dependency.getArtifact().trim()
              + ":"
              + dependency.getVersion().trim()
              + "\")";


    int indexOfDependenciesBlock = getIndexOfStringWithRegex(originFileAsString, "dependencies.*\\{");
      addTextToOriginFile(declaration, indexOfDependenciesBlock);
      tryRewriteOriginFile();
     this.dependencies.add(dependency);
   }


   @Override
   public <T extends AbstractPlugin> void addPlugin(T plugin) {
      String declaration = "id '" + plugin.getId().trim() + "'\n";

      addTextToOriginFile(declaration, getIndexOfStringWithRegex(originFileAsString, "plugins"));
      tryRewriteOriginFile();
      this.plugins.add(plugin);
   }

   private void addTextToOriginFile(String text, int indexOfWhereToWrite) {
      String secondOriginFileHalf = text + originFileAsString.substring(indexOfWhereToWrite);
      originFileAsString = originFileAsString.substring(0, indexOfWhereToWrite) + secondOriginFileHalf;
   }

   private void tryRewriteOriginFile() {
      try(FileWriter originFileWriter = getFileWriter()) {
         originFileWriter.write(originFileAsString);
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }

   private int getIndexOfStringWithRegex(String string, String regex){

      Matcher matcher = Pattern.compile(regex).matcher(string);
      matcher.find();
      return matcher.end();
   }

   private FileWriter tryInstantiateFileWriterFromFile(File file) {
      try {
         return new FileWriter(file);
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
   }


   @Override
   public String toString() {
      return "GradleBuildFile{" +
              "plugins=" + plugins +
              ", dependencies=" + dependencies +
              '}';
   }
}
