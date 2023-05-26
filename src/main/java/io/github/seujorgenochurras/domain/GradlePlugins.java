package io.github.seujorgenochurras.domain;

import java.util.ArrayList;
import java.util.List;

public class GradlePlugins {
   private List<GradlePlugin> pluginList = new ArrayList<>();

   public void addPlugin(GradlePlugin plugin){
      pluginList.add(plugin);
   }

   public List<GradlePlugin> getPluginList() {
      return pluginList;
   }

   public GradlePlugins setPluginList(List<GradlePlugin> pluginList) {
      this.pluginList = pluginList;
      return this;
   }

   @Override
   public String toString() {
      return "GradlePlugins{" +
              "pluginList=" + pluginList +
              '}';
   }
}
