plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")
    id("xyz.jpenilla.run-paper") version "2.1.0"
}

group = "de.greenman999"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation("dev.jorel:commandapi-bukkit-shade:9.0.1")
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
}

tasks {
    runServer {
        minecraftVersion("1.19.4")
    }
    shadowJar {
        relocate("dev.jorel.commandapi", "de.greenman999.daycounter.deps.commandapi")
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
    toolchain.languageVersion.set(JavaLanguageVersion.of(18))
}