plugins {
    `java-library`
    id("xyz.jpenilla.run-paper") version "2.3.0"
}

group = "dev.dejay"
version = "1.0.0"

val mcversion = "1.21.1"

repositories {
    mavenCentral()
    maven(url = "https://repo.papermc.io/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${mcversion}-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    runServer {
        minecraftVersion(mcversion)
    }

    compileJava {
        options.release = 21
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
        val props = mapOf(
            "version" to project.version,
        )
        inputs.properties(props)
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}

