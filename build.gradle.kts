plugins {
    java
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
}

repositories {
    mavenCentral()
}

tasks.bootJar {
    enabled = false
}

subprojects {
    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    @Suppress("SpellCheckingInspection")
    group = "moe.furryverse.server"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        // common
        if (project.name != "common") implementation(project(":common"))

        // spring
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-web-services")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
        implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
        implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

        // rpc
        // https://mvnrepository.com/artifact/org.apache.dubbo/dubbo-spring-boot-starter
        @Suppress("SpellCheckingInspection")
        implementation("org.apache.dubbo:dubbo-spring-boot-starter:3.2.4")

        // jetbrains
        implementation("org.jetbrains:annotations:22.0.0")

        // lombok
        @Suppress("SpellCheckingInspection") compileOnly("org.projectlombok:lombok")
        @Suppress("SpellCheckingInspection") annotationProcessor("org.projectlombok:lombok")

        // config parser
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    ext {
        set("springCloudVersion", "2022.0.3")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }
}