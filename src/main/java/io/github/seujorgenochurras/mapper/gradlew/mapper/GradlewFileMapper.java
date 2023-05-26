package io.github.seujorgenochurras.mapper.gradlew.mapper;

import io.github.seujorgenochurras.domain.*;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradlewFileMapper {

   private String gradleBuildFileAsString;

   private final GradlePlugins gradlePlugins = new GradlePlugins();

   private GradleDependencies dependencies = new GradleDependencies();


   private GradlewFileMapper(File gradleBuildFile) {
      this.gradleBuildFileAsString = FileUtils.getFileAsString(gradleBuildFile);
   }


   public static GradlewBuildFile mapFile(File file) {

    return GradlewFileMapper
              .startMappingFile(file)
              .removeComments()
              .mapPlugins()
              .mapDependencies()
              .buildFile();

   }

   public static GradlewFileMapper startMappingFile(File file) {
      return new GradlewFileMapper(file);
   }

   public GradlewFileMapper removeComments() {
      String multiLineAndInLineCommentsRegex = "/\\*((.|\\n)*?)\\*/|//.*";
      this.gradleBuildFileAsString = this.gradleBuildFileAsString.replaceAll(multiLineAndInLineCommentsRegex, "");
      return this;
   }

   public GradlewFileMapper mapPlugins() {
      List<String> plugins = getLinesOfPluginsBlock()
              .stream()
              .map(line -> {

                 String pluginDeclarationRegex = "(?<=id\\(\").*(?=\"\\))";
                 Matcher matcher = generateStringMatcherFromRegex(line, pluginDeclarationRegex);
                 return getStringOfMatcher(matcher);

              }).toList();

      for (String pluginName : plugins) {
         gradlePlugins.addPlugin(new GradlePlugin(pluginName));
      }
      return this;
   }

   public GradlewFileMapper mapDependencies() {
      this.dependencies = getDependencies();
      return this;
   }

   public GradlewBuildFile buildFile() {
      return new GradlewBuildFile()
              .setDependencies(dependencies)
              .setPlugins(gradlePlugins);
   }

   private GradleDependencies getDependencies() {
      List<String> dependenciesDeclaration = getDependencyDeclarations();

      GradleDependencies dependenciesFound = new GradleDependencies();


      dependenciesDeclaration.forEach(declaration -> {
         String declarationSyntaxRegex = "(?<=\\(\").*(?=\"\\))";

         Matcher declarationSyntaxMatcher = generateStringMatcherFromRegex(declaration, declarationSyntaxRegex);

         String declarationWithoutStringSyntax = getStringOfMatcher(declarationSyntaxMatcher);
         String[] declarationComponents = declarationWithoutStringSyntax.split(":");
         int groupNameIndex = 0;
         int artifactIndex = 1;
         int versionIndex = 2;
         String groupName = declarationComponents[groupNameIndex];
         String artifact = declarationComponents[artifactIndex];

         String version = declarationComponents.length == 3 ? declarationComponents[versionIndex] : "";

         dependenciesFound.addDependency(new GradleDependency()
                 .setGroupName(groupName)
                 .setArtifact(artifact)
                 .setVersion(version));
      });
      return dependenciesFound;
   }

   private List<String> getDependencyDeclarations() {
      List<String> dependenciesAsString = new ArrayList<>();

      getLinesOfDependenciesBlock().forEach(line -> {
         String dependencyDeclarationRegex = "(?<=testImplementation|implementation|runtime_only|testRuntimeOnly).*\\(\".*\"\\)";
         Matcher dependencyDeclarationMatcher = generateStringMatcherFromRegex(line, dependencyDeclarationRegex);
         dependenciesAsString.addAll(getAllMatchesOfMatcher(dependencyDeclarationMatcher));
      });

      return dependenciesAsString;
   }

   private List<String> getAllMatchesOfMatcher(Matcher matcher) {
      List<String> matchingStrings = new ArrayList<>();
      while (matcher.find()) {
         matchingStrings.add(matcher.group(0));
      }
      return matchingStrings;
   }

   private String getStringOfMatcher(Matcher matcher) {

      StringBuilder allMatches = new StringBuilder();

      while (matcher.find()) {
         allMatches.append(matcher.group(0));
      }
      return allMatches.toString();
   }

   private List<String> getLinesOfDependenciesBlock() {
      return getBlockLinesFromGradleFile("dependencies");
   }

   private List<String> getLinesOfPluginsBlock() {
      return getBlockLinesFromGradleFile("plugins");
   }

   private List<String> getBlockLinesFromGradleFile(String blockName) {
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
}
