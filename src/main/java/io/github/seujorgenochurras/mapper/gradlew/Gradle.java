package io.github.seujorgenochurras.mapper.gradlew;

import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Gradle extends DependencyMapper {
   protected String gradleBuildFileAsString;

   protected Gradle(File rootFile) {
      super(rootFile);
      this.gradleBuildFileAsString = FileUtils.getFileAsString(rootFile);
   }

      protected void removeComments() {
         String multiLineAndInLineCommentsRegex = "/\\*((.|\\n)*?)\\*/|//.*";
         this.gradleBuildFileAsString = this.gradleBuildFileAsString.replaceAll(multiLineAndInLineCommentsRegex, "");
      }

   protected List<String> getAllPluginDeclarations(){
       return getLinesOfPluginsBlock()
              .stream()
              .map(line -> {

                 String pluginDeclarationRegex = "(?<=id\\(\")";
                 Matcher matcher = generateStringMatcherFromRegex(line, pluginDeclarationRegex);
                 return getStringOfMatcher(matcher);

              }).toList();
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


}
