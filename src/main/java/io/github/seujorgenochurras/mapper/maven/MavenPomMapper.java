package io.github.seujorgenochurras.mapper.maven;

import io.github.seujorgenochurras.domain.AbstractPlugin;
import io.github.seujorgenochurras.domain.PluginDeclaration;
import io.github.seujorgenochurras.domain.dependency.Dependency;
import io.github.seujorgenochurras.domain.dependency.DependencyBuilder;
import io.github.seujorgenochurras.mapper.DependencyManagerFile;
import io.github.seujorgenochurras.mapper.DependencyMapper;
import io.github.seujorgenochurras.utils.BetterNodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class MavenPomMapper extends DependencyMapper {

   private static final Logger logger = Logger.getLogger(MavenPomMapper.class.getName());
   private Document pomFileDocument;
   private List<Dependency> dependencies = new ArrayList<>();
   private List<AbstractPlugin> plugins = new ArrayList<>();

   public MavenPomMapper(File rootFile) {
      super(rootFile);
   }

   public static void main(String[] args) throws Exception {
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
      File file = new File("dependency-file-example/pom.xml");
      Document document = documentBuilder.parse(file);


      document.getElementsByTagName("groupId").item(1).setTextContent("MEU PAU DE ASSA");
      var domSource1 = new DOMSource(document);

      Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
      transformer.transform(domSource1, new StreamResult(file));
   }

   @Override
   protected DependencyManagerFile map() {
      tryInitPomFileDocument();
      mapDependencies();
      mapPlugins();
      return null;
   }

   @Override
   protected void mapDependencies() {
      this.dependencies = getMavenFileDependencies();
   }

   @Override
   protected void mapPlugins() {
      //TODO
   }

   private List<Dependency> getMavenFileDependencies() {
      BetterNodeList dependencyNodeList = new BetterNodeList(getDependencyNodeList());
      List<Dependency> dependenciesFound = new ArrayList<>();

      dependencyNodeList.forEachChild(childNode -> {
         BetterNodeList childNodes = new BetterNodeList(childNode.getChildNodes());
         String artifact = childNodes.getChildNodeByName("artifactId").getTextContent();
         String groupName = childNodes.getChildNodeByName("groupId").getTextContent();
         String version;
         Node versionNode = childNodes.getChildNodeByName("version");
         if (versionNode == null) version = "";
         else version = versionNode.getTextContent();

         dependenciesFound.add(DependencyBuilder
                 .startBuild()
                 .artifact(artifact)
                 .group(groupName)
                 .version(version)
                 .buildResult());

      });
      return dependenciesFound;
   }

   private NodeList getDependencyNodeList() {
      return this.pomFileDocument.getElementsByTagName("dependency");
   }
   private NodeList getPluginNodeList() {
      return this.pomFileDocument.getElementsByTagName("plugin");
   }

   private void tryInitPomFileDocument() {
      try {
         initPomFileDocument();
      } catch (ParserConfigurationException | IOException | SAXException e) {
         logger.severe(e.getMessage());
         e.printStackTrace();
      }
   }

   private void initPomFileDocument() throws ParserConfigurationException, IOException, SAXException {
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
      this.pomFileDocument = documentBuilder.parse(rootFile);
   }
}



