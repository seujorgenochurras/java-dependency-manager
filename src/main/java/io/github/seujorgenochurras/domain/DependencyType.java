package io.github.seujorgenochurras.domain;

public enum DependencyType {
   TEST_IMPLEMENTATION("testImplementation"),
   TEST_RUNTIME_ONLY("testRuntimeOnly"),

   TEST_COMPILE_ONLY("testCompileOnly"),

   RUNTIME_ONLY("runtimeOnly"),
   IMPLEMENTATION("implementation"),

   API("api"),

   COMPILE_ONLY("compileOnly"),
   COMPILE_ONLY_API("compileOnlyApi");

   public final String typeName;

   DependencyType(String typeName) {
      this.typeName = typeName;
   }
}