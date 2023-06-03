package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.domain.manager.GradleBuildFileBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradleMapper extends DependencyMapper {
   protected String gradleBuildFileAsString;

   public GradleMapper(File rootFile) {
      super(rootFile);
      this.gradleBuildFileAsString = FileUtils.getFileAsString(rootFile);
   }

   @Override
   protected DependencyManagerFile map() {
      this.removeComments();
      mapDependencies();
      mapPlugins();

      return GradleBuildFileBuilder.startBuild()
              .dependencies(this.dependencies)
              .plugins(this.plugins)
              .originFile(this.rootFile)
              .getBuild();
   }

   @Override
   protected void mapDependencies() {

      getDependencyDeclarations().forEach(dependencyDeclaration -> {
         dependencyDeclaration = dependencyDeclaration.replaceAll("[()\"]", "");

         String[] dependencyDeclarationComponents = dependencyDeclaration.split(":");
         String dependencyGroup = dependencyDeclarationComponents[0];
         String dependencyArtifact = dependencyDeclarationComponents[1];
         String dependencyVersion = dependencyDeclarationComponents.length == 3 ? dependencyDeclarationComponents[2] : "";

         this.dependencies.add(DependencyBuilder
                 .startBuild()
                 .group(dependencyGroup)
                 .artifact(dependencyArtifact)
                 .version(dependencyVersion)
                 .buildResult()
         );
      });
   }

   @Override
   protected void mapPlugins() {
      getAllPluginDeclarations().forEach(pluginDeclaration -> {
         pluginDeclaration = pluginDeclaration.replaceAll("[()\"]", "");
         this.plugins.add(new AbstractPlugin().setId(pluginDeclaration));
      });
   }

   private void removeComments() {
      String multiLineAndInLineCommentsRegex = "/\\*((.|\\n)*?)\\*/|//.*";
      this.gradleBuildFileAsString = this.gradleBuildFileAsString.replaceAll(multiLineAndInLineCommentsRegex, "");
   }

   protected List<String> getAllPluginDeclarations() {
      List<String> pluginsAsString = new ArrayList<>();
      getLinesOfPluginsBlock().forEach(line -> {
         String pluginDeclarationRegex = "(?<=id).*['\"]";
         Matcher matcher = generateStringMatcherFromRegex(line, pluginDeclarationRegex);
         pluginsAsString.addAll(getAllMatchesOfMatcher(matcher));
      });
      return pluginsAsString;
   }

   protected List<String> getDependencyDeclarations() {
      List<String> dependenciesAsString = new ArrayList<>();

      getLinesOfDependenciesBlock().forEach(line -> {
         String dependencyDeclarationRegex = "(?<=testImplementation|implementation|runtime_only|testRuntimeOnly).*".trim();
         Matcher dependencyDeclarationMatcher = generateStringMatcherFromRegex(line, dependencyDeclarationRegex);
         dependenciesAsString.addAll(getAllMatchesOfMatcher(dependencyDeclarationMatcher));
      });

      return dependenciesAsString;
   }

   protected List<String> getBlockLinesFromGradleFile(String blockName) {
      String blockRegex = generateRegexOfBlock(blockName);

      Matcher matcher = generateGradleMatcherFromRegex(blockRegex);

      return getAllMatchesOfMatcher(matcher);
   }

   private Matcher generateGradleMatcherFromRegex(String regex) {
      return generateStringMatcherFromRegex(gradleBuildFileAsString, regex);
   }

   private Matcher generateStringMatcherFromRegex(String string, String regex) {
      Pattern pattern = Pattern.compile(regex);
      return pattern.matcher(string);
   }

   private String generateRegexOfBlock(String blockName) {
      return "(" + blockName + ".*)\\{([^}]+)}";
   }

   private List<String> getAllMatchesOfMatcher(Matcher matcher) {
      List<String> matchingStrings = new ArrayList<>();
      while (matcher.find()) {
         matchingStrings.add(matcher.group(0));
      }
      return matchingStrings;
   }

   private List<String> getLinesOfDependenciesBlock() {
      return getBlockLinesFromGradleFile("dependencies");
   }

   private List<String> getLinesOfPluginsBlock() {
      return getBlockLinesFromGradleFile("plugins");
   }

}
