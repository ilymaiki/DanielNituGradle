plugins {
    id("java")
    application
}

application {
    mainClass.set("com.daninitu.tema4gradle.Main");
}

group = "com.daninitu.tema4gradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencies {
    implementation(platform("dev.langchain4j:langchain4j-bom:1.10.0"))
    implementation("dev.langchain4j:langchain4j-open-ai")
}

tasks.test {
    useJUnitPlatform()
}

// Versión de Ollama.
tasks.register<Exec>("ollamaVersion") {
    commandLine("cmd", "/c", "ollama --version")
}

// Modelos cargados.
tasks.register<Exec>("ollamaPs") {
    commandLine("cmd", "/c", "ollama ps");
}

// Tarea combinada.
tasks.register("llmInfo") {
    dependsOn("ollamaVersion", "ollamaPs")
    doLast { println("Demo finalizada.") }
}

