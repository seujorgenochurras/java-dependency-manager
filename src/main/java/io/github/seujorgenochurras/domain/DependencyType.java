package io.github.seujorgenochurras.domain;

public enum DependencyType {
   TEST_IMPLEMENTATION,
   TEST_RUNTIME_ONLY,

   //TODO TEST_COMPILE_ONLY,

   RUNTIME_ONLY,
   IMPLEMENTATION,

   //TODO  API,

   COMPILE_ONLY,
   //TODO COMPILE_ONLY_API,

}