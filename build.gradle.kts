plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.3"
}

@Suppress("SpellCheckingInspection")
group = "moe.furryverse"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// spring web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("org.springframework.security:spring-security-crypto:6.2.1")

	// spring database
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// mail
	implementation("org.springframework.boot:spring-boot-starter-mail")

	// http client
	implementation("com.squareup.okhttp3:okhttp:4.12.0")

	// text diff
	implementation("io.github.java-diff-utils:java-diff-utils:4.12")

	// search index
	implementation("com.meilisearch.sdk:meilisearch-java:0.11.5")

	// storage
	// https://mvnrepository.com/artifact/com.qiniu/qiniu-java-sdk
	implementation ("com.qiniu:qiniu-java-sdk:7.15.0")

	// config
	implementation("org.springframework.boot:spring-boot-starter-aop")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// jetbrains
	implementation("org.jetbrains:annotations:24.0.1")

	// lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<Test> {
	useJUnitPlatform()
}