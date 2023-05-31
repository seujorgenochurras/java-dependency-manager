package io.github.seujorgenochurras.file;

import java.io.File;
import java.util.*;
import java.util.function.Supplier;

public class FileSearcher {
   private FileSearcher(SearchPlaces searchPlace) {
      this.searchPath = searchPlace.placePath;
   }

   public FileSearcher(String searchPath) {
      this.searchPath = searchPath;
   }

   private final String searchPath;

   private final List<String> filesNameToSearch = new ArrayList<>();

   private Supplier<? extends RuntimeException> notFoundSupplier = () -> new NoSuchElementException("File not found");

   public static FileSearcher searchIn(SearchPlaces searchPlace) {
      return new FileSearcher(searchPlace);
   }

   public static FileSearcher searchForFile(String fileName) {

      FileSearcher fileSearcher = new FileSearcher(SearchPlaces.PROJECT_ROOT);
      return fileSearcher.addFileToSearch(fileSearcher.searchPath + "/" + fileName);
   }

   private FileSearcher addFileToSearch(String fileNameAndPath) {
      this.filesNameToSearch.add(fileNameAndPath);
      return this;
   }

   public FileSearcher ifNotFoundSearchFor(String fileName) {
      return addFileToSearch(this.searchPath + "/" + fileName);
   }


   private File searchFile(String fileNameAndPath) {
      return new File(fileNameAndPath);
   }

   public File getSearchResult() {

      File fileFound = null;

      for (String fileNameAndPath : filesNameToSearch) {
         if (!Objects.isNull(fileFound)) break;

         fileFound = searchFile(fileNameAndPath);
      }

      if (Objects.isNull(fileFound)) {
         throw this.notFoundSupplier.get();
      }

      return fileFound;
   }

   public <T extends RuntimeException> FileSearcher ifNotFoundThrow(Supplier<T> notFoundSupplier) {
      this.notFoundSupplier = notFoundSupplier;
      return this;
   }
   /*
   FileSearcher
              .searchIn(SearchPlaces.PROJECT_ROOT)
              .searchForFile("build.gradle.kts")

              .ifNotFoundSearchFor("build.gradle")
              .ifNotFoundSearchFor("maven.pom")
              .ifNotFoundThrow(new FileNotFoundException("No dependency manager file found"))
              .getSearchResult();
    */
}
