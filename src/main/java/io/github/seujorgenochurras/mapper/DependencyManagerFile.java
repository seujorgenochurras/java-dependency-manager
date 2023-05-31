package io.github.seujorgenochurras.mapper;

import io.github.seujorgenochurras.domain.Dependency;
import io.github.seujorgenochurras.domain.Plugin;

import java.util.List;

public interface DependencyManagerFile {
   void addDependency(Dependency gradleDependency);

   void addPlugin(Plugin plugin);

   List<Plugin> getPlugins();

   List<Dependency> getDependencies();

}
