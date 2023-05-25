package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.domain.GradlewBuildFile;
import io.github.seujorgenochurras.mapper.gradlew.clean.GradlewStringCollectionCleaner;
import io.github.seujorgenochurras.mapper.gradlew.clean.ImplementationStringCleaner;
import io.github.seujorgenochurras.mapper.gradlew.validate.GradleValidatorChain;
import io.github.seujorgenochurras.mapper.gradlew.validate.string.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GradlewFileMapper {

   public GradlewBuildFile mapFile(File file) throws FileNotFoundException {
      List<String> buildGradlewLines = new ArrayList<>();
      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
         buildGradlewLines.add(scanner.nextLine());

      }
      buildGradlewLines = GradlewStringCollectionCleaner
              .startCleaning(buildGradlewLines)
              .removeComments()
              .trim()
              .getCleanList();

      return new GradlewBuildFile();
   }

   private ArrayList<String> getValidDependenciesOf(String[] dependenciesDeclarationsToCheck) {

      ArrayList<String> dependenciesDeclarations = new ArrayList<>();

      for (String probableDependency : dependenciesDeclarationsToCheck) {
         if (isStringImplementation(probableDependency)) {
            dependenciesDeclarations.add(probableDependency);
         }
      }

      return dependenciesDeclarations;
   }

   public String getDependenciesFunctionFromString(String rawFile) {
      String dependenciesPlace = rawFile.split("dependencies")[1];

      int openCurlyBracesCount = -1;
      boolean isFirstCurlyBrace = true;
      String result = "";
      for (char letter : dependenciesPlace.toCharArray()) {

         if(openCurlyBracesCount == 0) break;
         if(letter == '{'){
            if(isFirstCurlyBrace){
               openCurlyBracesCount++;
               isFirstCurlyBrace = false;
            }
            openCurlyBracesCount++;
            continue;
         }else if(letter == '}'){
            openCurlyBracesCount--;
            continue;
         }
         result += letter;
      }
      System.out.println(getPossibleDependenciesImplementations(result));
      return result;
   }
   private boolean isStringImplementation(String possibleImplementationString){
      GradleValidatorChain<String> dependencyDeclarationValidator = GradleValidatorChain
              .startValidationChainOf(String.class)
              .addValidator(new ValidateStringNotBlank())
              .addValidator(new ValidateStringIsNotComment())
              .addValidator(new ValidateStringStartsWithDependencyType())
              .addValidator(new ValidateStringContainsDependencyGroup())
              .addValidator(new ValidateStringContainsDependencyArtifact());

      return dependencyDeclarationValidator.validate(possibleImplementationString);
   }

   private List<String> getPossibleDependenciesImplementations(String junk){
      List<String> possibleDependencies = new ArrayList<>();

      int minImplementationLength = 24; //It could be even higher, but who knows
      for (String s : junk.split("(?= [tiRIrCaTcA])")) {
         if(s.length() > minImplementationLength) possibleDependencies.add(s.replaceAll("\\/\\/|\\/\\*|\\*\\/", "").trim());
      }
      return possibleDependencies;
   }
}
