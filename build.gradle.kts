plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.1.0"
}

group = "de.greenman999"
version = "1.2.1"

repositories {
    mavenCentral()
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("dev.jorel:commandapi-bukkit-core:9.5.1")
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
}

tasks {
    runServer {
        minecraftVersion("1.21")
    }
    processResources {
        inputs.property("version", project.version)
        filesMatching("paper-plugin.yml") {
            expand("version" to project.version)
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}