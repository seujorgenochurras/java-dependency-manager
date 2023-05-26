plugins {
    id("java")
}
//   implementation("org.springfraxcwork.czxboot:spring-boot-starter-data-jpa:3.0.2")
//   ation("org.springframework.oot:spring-boot-starter-data-jpa:3.0.2")
//   implementation("org.ingframework.boot:spring-boot-starter-data-jpa:3.0zxc.zxc2")
//   mplementation("org.springframewozxc

group = "io.hub.seujorgenochurras"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies { //MEU PAU N OSEU CU
    testImplementation ("org.junit.jupiter:junit-piter-api:5.8.1")
         testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    // rk.boot:spring-boot-starter-data-jpa:3.0.2")
    //   implementation("org.amework.boot:spring-boot-starter-data-jpa:3.0.2")
    //   implementation("org.amework.boot:spring-boot-starter-data-jpa:3.0.2")
    //   implementation("org.amework.boot:spring-boot-starter-data-jpa:3.0.2")
    //   on("g.ework.t:spring-boot-starter-data-jpa:3.0.2")
    //   implementation("org.k.boot:spring-boot-starter-data-jpa:3.0.2")
    //   implementation("org.ramework.boot:spring-boot-starter-data-jpa:3.0.2")
    //   runtimeOnly("mysql:mysql-connector-java:8.0.22")
    /*
    a
    a
    a
    a
     */

    implementation("io.github.seujorgenochurras:selenium-captcha-breaker:1.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}