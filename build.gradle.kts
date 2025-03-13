/*
 * This file was generated by the Gradle "init" task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

group = "dev.aomegax"
version = "0.0.0"
description = "EchoBoard - BE"
java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    id("java")
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

//val stringBootVersion = "3.4.3"

dependencyManagement {
    imports { mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.3") }

}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.2.0")

    // OpenAPI e Utility
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
    implementation("org.modelmapper:modelmapper:3.2.2")

    // H2
    runtimeOnly("com.h2database:h2:2.3.232")

    // Lombok (con annotation processor!)
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    // ModelMapper
    implementation("org.modelmapper:modelmapper:3.2.2")

    // AspectJ
    implementation("org.aspectj:aspectjrt:1.9.22.1")
    compileOnly("org.aspectj:aspectjweaver:1.9.22.1")

    // Logback ECS Encoder
    implementation("co.elastic.logging:logback-ecs-encoder:1.6.0")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.0")


//    api libs.org.springframework.boot.spring.boot.starter.web
//    api libs.org.springframework.boot.spring.boot.starter.validation
//    api libs.org.springframework.boot.spring.boot.configuration.processor
//    api libs.org.springframework.boot.spring.boot.starter.actuator
//    api libs.org.springframework.boot.spring.boot.starter.data.jpa
//    api libs.org.springframework.data.spring.data.jpa
//    api libs.org.springframework.boot.spring.boot.starter.cache
//    api libs.com.github.ben.manes.caffeine.caffeine
//    api libs.org.springdoc.springdoc.openapi.starter.webmvc.ui
//    api libs.org.hibernate.orm.hibernate.core
//    api libs.org.springframework.cloud.spring.cloud.starter.openfeign
//    api libs.org.modelmapper.modelmapper
//    api libs.org.projectlombok.lombok
//    api libs.co.elastic.logging.logback.ecs.encoder
//    runtimeOnly libs.org.springframework.boot.spring.boot.devtools
//    runtimeOnly libs.com.h2database.h2
//    testImplementation libs.org.springframework.boot.spring.boot.starter.test
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java")
        }
    }
    test {
        java {
            srcDirs("src/test/java")
        }
    }
}

//val appVersion = project.version.toString()
//
//tasks.withType<ProcessResources> {
//    // Usa una stringa semplice (appVersion) che può essere serializzata
//    inputs.property("appVersion", "1.3.3")
//
//    // Sostituisci il placeholder nel file application.properties
//    filesMatching("**/application.properties") {
//        expand(mapOf("appVersion" to "1.3.3"))
//    }
//}

tasks.withType<Test> {
    useJUnitPlatform()
}
