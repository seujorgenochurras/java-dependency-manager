package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.domain.GradlewBuildFile;
import io.github.seujorgenochurras.mapper.gradlew.GradlewMapperChain;

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

   private String getDependenciesString(String rawFile){
      String[] dependenciesPlace = rawFile.split("dependencies")[1].split(" ");

      int curlyBracesCount = -1;
      boolean isFirstCurlyBrace = true;
      String[] result = dependenciesPlace.toString().split(" ");


      GradlewMapperValidationChain validationChain = GradlewMapperValidationChain
              .startChain()
              .addValidator(new ValidateStringNotBlank())
              .addValidator(new ValidateStringIsNotComment())
              .addValidator(new ValidateStringStartsWithDependencyDeclarator());

      ArrayList<String> dependencies = new ArrayList<>();

      for(String probableDependency : result){

            dependencies.add(probableDependency);
         }

      }



      for(char letter : dependenciesPlace.toString().toCharArray()){
         if(curlyBracesCount == 0) {
            break;
         }
         if(letter == '{') {
            if(isFirstCurlyBrace) {
               curlyBracesCount++;
               isFirstCurlyBrace = false;
            }
            curlyBracesCount++;
            continue;
         }
         else if(letter == '}'){
            curlyBracesCount--;
            continue;
         }
            result.append(letter);

      }
      return result.toString();
   }
}