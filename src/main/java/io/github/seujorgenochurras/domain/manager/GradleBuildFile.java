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

   public GradleBuildFile setPlugins(List<AbstractPlugin> plugins) {
      this.plugins = plugins;
      return this;
   }

   public GradleBuildFile setDependencies(List<Dependency> dependencies) {
      this.dependencies = dependencies;
      return this;
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
      //TODO add dependencyType

      String declaration = "implementation \""
              + dependency.getGroupName().trim()
              + ":"
              + dependency.getArtifact().trim()
              + ":"
              + dependency.getVersion().trim()
              + "\"\n";


      getIndexOfStringWithRegex(originFileAsString);
      addTextToOriginFile(declaration, getIndexOfBlock("dependencies") + 1);
      tryRewriteOriginFile();
      this.dependencies.add(dependency);
   }


   @Override
   public <T extends AbstractPlugin> void addPlugin(T plugin) {
      String declaration = "id '" + plugin.getId().trim() + "'\n";

      addTextToOriginFile(declaration, getIndexOfBlock("plugins"));
      tryRewriteOriginFile();
      this.plugins.add(plugin);
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
   private int getIndexOfStringWithRegex(String string){

      System.out.println(string);
      Matcher matcher = Pattern.compile("dependencies.*\\{").matcher(string);
      System.out.println(matcher.find());
      System.out.println(matcher.groupCount());

      return 10;
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
