import com.github.gradle.node.npm.task.NpmTask

plugins {
    java
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.node-gradle.node") version "7.1.0"
}

group = "de.tzerr"
version = "0.0.1-SNAPSHOT"
description = "mucken-webapp"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("de.tzerr:mucken-core:0.1.0-SNAPSHOT")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<NpmTask>("clientInstall") {
    description = "Installs NPM dependencies of the client"
    workingDir = file("$projectDir/src/main/client")
    args.set(listOf("install"))
}

tasks.register<NpmTask>("clientBuild") {
    dependsOn("clientInstall")
    description = "Build client app and deploy to spring boot app"
    workingDir = file("$projectDir/src/main/client")
    args.set(listOf("run", "build"))
}

tasks.compileJava {
    //dependsOn("clientBuild")
}
