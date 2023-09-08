dependencies {
    // curator
    implementation("org.apache.curator:curator-recipes:5.5.0")
    implementation("org.apache.curator:curator-framework:5.5.0")
    implementation("org.apache.curator:curator-x-discovery:5.5.0")

    // rpc
    @Suppress("SpellCheckingInspection") implementation("org.apache.dubbo:dubbo-bom:3.2.5")
    @Suppress("SpellCheckingInspection") implementation("org.apache.dubbo:dubbo-spring-boot-starter:3.2.5")
    @Suppress("SpellCheckingInspection") implementation("org.apache.dubbo:dubbo-dependencies-zookeeper-curator5:3.2.5")
}