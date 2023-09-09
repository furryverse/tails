dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-web-services")

    // mongodb
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // redis
    implementation("org.springframework.data:spring-data-redis")

    // minio
    implementation("io.minio:minio:8.5.5")
    @Suppress("SpellCheckingInspection") implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // jetbrains
    implementation("org.jetbrains:annotations:24.0.1")

    // lombok
    @Suppress("SpellCheckingInspection") compileOnly("org.projectlombok:lombok")
    @Suppress("SpellCheckingInspection") annotationProcessor("org.projectlombok:lombok")

    // config parser
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // 依赖其他的子项目

    @Suppress("SpellCheckingInspection")
    arrayOf("alnitak", "arcturus", "ascella", "borealis", "common", "hecatebolus", "mira", "nanto", "naos", "polis")
            .forEach { implementation(project(":${it}")) }
}