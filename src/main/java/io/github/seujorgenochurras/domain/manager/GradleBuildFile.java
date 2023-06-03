package io.github.seujorgenochurras.domain.manager;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.PluginDeclaration;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyDeclaration;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.seujorgenochurras.utils.StringUtils.*;

public class GradleBuildFile implements DependencyManagerFile {
   private List<DependencyDeclaration> dependencies;
   private List<PluginDeclaration> plugins;
   private File originFile;
   private String originFileAsString;
   private FileWriter fileWriter;

   @Override
   public List<Dependency> getDependencies() {
      return dependencies.stream()
              .map(DependencyDeclaration::toDependencyObject)
              .collect(Collectors.toList());
   }

   @Override
   public List<AbstractPlugin> getPlugins() {
      return plugins.stream()
              .map(PluginDeclaration::toPluginObject).toList();
   }

   public void setPlugins(List<PluginDeclaration> plugins) {
      this.plugins = plugins;
   }

   public void setDependencies(List<DependencyDeclaration> dependencies) {
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


      int indexOfDependenciesBlock = getIndexOfDependenciesBlock();
      addTextToOriginFile(declaration, indexOfDependenciesBlock);
      tryRewriteOriginFile();
      //this.dependencies.add(dependency);
   }

   @Override
   public <T extends AbstractPlugin> void addPlugin(T plugin) {
      String declaration = "id '" + plugin.getId().trim() + "'\n";

      addTextToOriginFile(declaration, getIndexOfStringWithRegex(originFileAsString, "plugins"));
      tryRewriteOriginFile();
    //  this.plugins.add(plugin);
   }

   @Override
   public void removeDependency(Dependency dependency) {

   }

   @Override
   public <T extends AbstractPlugin> void removePlugin(T plugin) {

   }

   private int getIndexOfDependenciesBlock(){
      return getIndexOfStringWithRegex(originFileAsString, "dependencies.*\\{");
   }

   private void addTextToOriginFile(String text, int indexOfWhereToWrite) {
      String secondOriginFileHalf = text + originFileAsString.substring(indexOfWhereToWrite);
      originFileAsString = originFileAsString.substring(0, indexOfWhereToWrite) + secondOriginFileHalf;
   }

   private void tryRewriteOriginFile() {
      try (FileWriter originFileWriter = getFileWriter()) {
         originFileWriter.write(originFileAsString);
      } catch (IOException e) {
         throw new IllegalStateException(e);
      }
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
