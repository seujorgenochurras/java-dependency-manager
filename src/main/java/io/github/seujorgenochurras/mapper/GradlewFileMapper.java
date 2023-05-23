package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.domain.GradlewBuildFile;
import io.github.seujorgenochurras.mapper.gradlew.validator.GradleValidatorChain;
import io.github.seujorgenochurras.mapper.gradlew.validator.string.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradlewFileMapper {

   public GradlewBuildFile mapFile(File file) throws FileNotFoundException {
      StringBuilder fileAsString = new StringBuilder();

      Scanner scanner = new Scanner(file);

      while (scanner.hasNext()) {
         fileAsString.append(scanner.next());
      }
      System.out.println(getDependenciesString(fileAsString.toString()));

      return new GradlewBuildFile();
   }

   private String getDependenciesString(String rawFile) {
      String[] dependenciesPlace = rawFile.split("dependencies")[1].split(" ");

      int curlyBracesCount = -1;
      boolean isFirstCurlyBrace = true;
      String[] result = dependenciesPlace.toString().split(" ");


      GradleValidatorChain<String> dependencyDeclarationValidator = GradleValidatorChain
              .startValidationChainOf(String.class)
              .addValidator(new ValidateStringNotBlank())
              .addValidator(new ValidateStringIsNotComment())
              .addValidator(new ValidateStringStartsWithDependencyType())
              .addValidator(new ValidateStringContainsDependencyGroup())
              .addValidator(new ValidateStringContainsDependencyArtifact());

      ArrayList<String> dependenciesDeclarations = new ArrayList<>();

      for (String probableDependency : result) {
         if (dependencyDeclarationValidator.validate(probableDependency)) {
            dependenciesDeclarations.add(probableDependency);
         }
      }

//      for(char letter : dependenciesPlace.toString().toCharArray()){
//         if(curlyBracesCount == 0) {
//            break;
//         }
//         if(letter == '{') {
//            if(isFirstCurlyBrace) {
//               curlyBracesCount++;
//               isFirstCurlyBrace = false;
//            }
//            curlyBracesCount++;
//            continue;
//         }
//         else if(letter == '}'){
//            curlyBracesCount--;
//            continue;
//         }
//            result.append(letter);
//
//      }
//      return result.toString();
//   }
      return "";

   }
}