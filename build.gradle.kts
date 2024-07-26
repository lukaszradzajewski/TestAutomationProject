plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.20.0")
    testImplementation("org.testng:testng:7.10.1")
    testImplementation("io.cucumber:cucumber-java:7.17.0")
    testImplementation("io.cucumber:cucumber-testng:7.17.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.8.0")
    testImplementation("org.json:json:20240303")
    testImplementation("io.qameta.allure:allure-testng:2.27.0")
    testImplementation("io.cucumber:cucumber-guice:7.17.0")
    testImplementation("com.google.inject:guice:7.0.0")
    testImplementation("joda-time:joda-time:2.12.7")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("net.andreinc:mockneat:0.4.8")
}

tasks.test {
    useTestNG()
}