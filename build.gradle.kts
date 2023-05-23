plugins {
    id("java")
}

group = "io.github.seujorgenochurras"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies { //MEU PAU N OSEU CU
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
 testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")

  // implementation("org.springframework.boot:spring-boot-starter")
 //    implementation("org.springframework.boot:spring-boot-devtools")
 //   implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.2")
  //   runtimeOnly("mysql:mysql-connector-java:8.0.22")/* */

    implementation("io.github.seujorgenochurras:selenium-captcha-breaker:1.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}