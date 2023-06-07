package io.github.seujorgenochurras.mapper.maven;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.github.seujorgenochurras.file.DependencyFileNotFoundException;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MavenPomMapper extends DependencyMapper {
   private PomFileMapped pomFileMapped;

   public MavenPomMapper(File rootFile) {
      super(rootFile);
   }

   @Override
   protected DependencyManagerFile map() {
      initPomFile();

      return null;
   }

   private void initPomFile() {
      String rawXmlFile = FileUtils.getFileAsString(new File("dependency-file-example/pom.xml"));
      //  String rawXmlFile = FileUtils.getFileAsString(rootFile));
      this.pomFileMapped = tryGeneratePomFileMapped(rawXmlFile);
   }

   private PomFileMapped tryGeneratePomFileMapped(String rawFile) {
      try {
         return new XmlMapper().readValue(rawFile, PomFileMapped.class);
      } catch (JsonProcessingException e) {
         Logger.getLogger(MavenPomMapper.class.getName()).severe("An error occurred while trying to parse xml file");
         e.printStackTrace();
         throw new DependencyFileNotFoundException(e);
      }
   }

   @Override
   protected void mapDependencies() {

   }

   @Override
   protected void mapPlugins() {

   }

   @JacksonXmlRootElement(localName = "project")
   @JsonIgnoreProperties(ignoreUnknown = true)
   private static final class PomFileMapped {

      @JacksonXmlProperty(localName = "dependencies")
      private PomFileDependencies dependencies;


      @Override
      public String toString() {
         return "PomFileMapped{" +
                 "dependencies=\n" + dependencies +
                 '}';
      }

      private static final class PomFileDependencies {
         @JacksonXmlElementWrapper(useWrapping = false)
         @JacksonXmlProperty(localName = "dependency")
         private final List<PomFileDependency> dependencies = new ArrayList<>();

         @Override
         public String toString() {
            return "PomFileDependencies{" +
                    "\ndependencies=" + dependencies +
                    '}';
         }

         private static final class PomFileDependency {

            @JacksonXmlProperty(localName = "groupId")
            private String groupName;
            @JacksonXmlProperty(localName = "artifactId")
            private String artifactName;
            @JacksonXmlProperty(localName = "version")
            private String version;

            public String getGroupName() {
               return groupName;
            }

            public PomFileDependency setGroupName(String groupName) {
               this.groupName = groupName;
               return this;
            }

            public String getArtifactName() {
               return artifactName;
            }

            public PomFileDependency setArtifactName(String artifactName) {
               this.artifactName = artifactName;
               return this;
            }

            public String getVersion() {
               return version;
            }

            public PomFileDependency setVersion(String version) {
               this.version = version;
               return this;
            }

            @Override
            public String toString() {
               return "PomFileDependency{\n" +
                       "groupName='" + groupName + '\'' +
                       ",\nartifactName='" + artifactName + '\'' +
                       ",\nversion='" + version + '\'' +
                       "}\n\n";
            }
         }
      }

   }
}
