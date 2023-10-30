plugins {
	java
	id("org.springframework.boot") version "3.1.4"
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

	// spring database
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// mail
	implementation("org.springframework.boot:spring-boot-starter-mail")

	// minio
	implementation("io.minio:minio:8.5.5")
	@Suppress("SpellCheckingInspection") implementation("com.squareup.okhttp3:okhttp:4.11.0")

	// text diff
	implementation("io.github.java-diff-utils:java-diff-utils:4.12")

	// config
	implementation("org.springframework.boot:spring-boot-starter-aop")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	// jetbrains
	implementation("org.jetbrains:annotations:24.0.1")

	// lombok
	@Suppress("SpellCheckingInspection") compileOnly("org.projectlombok:lombok")
	@Suppress("SpellCheckingInspection") annotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}