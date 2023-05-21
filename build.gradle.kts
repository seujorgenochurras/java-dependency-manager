plugins {
    id("java")
}

group = "io.github.seujorgenochurras"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies     { //MEU PAU N OSEU CU {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}