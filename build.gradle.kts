plugins {
    id ("java-library")
    id ("maven-publish")
    id ("signing")
}
//   implementation("org.springfraxcwork.czxboot:spring-boot-starter-data-jpa:3.0.2")
//   ation("org.springframework.oot:spring-boot-starter-data-jpa:3.0.2")
//   implementation("org.ingframework.boot:spring-boot-starter-data-jpa:3.0zxc.zxc2")
//   mplementation("org.springframewozxc

group "io.github.seujorgenochurras"
version "0.1.1"
repositories {
    mavenCentral()
}

dependencies {

}

publishing {
    publications {
        create<MavenPublication>("mavenJava"){
            groupId = "io.github.seujorgenochurras"
            artifactId = "java-dependency-manager"
            version = "0.1.1"
            from (components["java"])

                    pom {
                        name.set("Java Dependency Manager")
                        description.set("A library to manage either gradle (kotlin/groovy) or Maven (pom) files")
                        url.set("https://github.com/seujorgenochurras/java-dependency-manager")

                        licenses {
                            license {
                                name.set("MIT License")
                                url.set("http://www.opensource.org/licenses/mit-license.php")
                            }
                        }
                        developers {
                            developer {
                                id.set("LILJ")
                                name.set("Little Jhey")
                                email.set("jjotinha_oficial@outlook.com")
                            }
                        }
                        scm {
                            connection.set("scm:git:git://github.com/java-dependency-manager")
                            developerConnection.set("scm:git:git://github.com/java-dependency-manager")
                            url.set("https://github.com/seujorgenochurras/java-dependency-manager")
                        }
                    }
        }
    }
    repositories {
        maven{

            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials{
                username = project.properties["repoUser"].toString()
                password = project.properties["repoPassword"].toString();
            }
        }
    }
}

java{
    withJavadocJar()
    withSourcesJar()
}
signing {
    sign(publishing.publications["mavenJava"])
}



tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
