import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.7.1"
  id("xyz.jpenilla.run-paper") version "2.3.0" // Adds runServer and runMojangMappedServer tasks for testing
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1" // Generates plugin.yml based on the Gradle config
}

group = "noppe.minecraft.arena"
version = "1.0-SNAPSHOT"

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 21 on systems that only have JDK 11 installed for example.
  toolchain.languageVersion = JavaLanguageVersion.of(21)
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

//
//repositories {
//    mavenCentral()
//    maven {
//        name = "papermc-repo"
//        url = "https://repo.papermc.io/repository/maven-public/"
//    }
//    maven {
//        name = "sonatype"
//        url = "https://oss.sonatype.org/content/groups/public/"
//    }
//}

dependencies {
  paperweight.paperDevBundle("1.21-R0.1-SNAPSHOT")
  // paperweight.foliaDevBundle("1.21-R0.1-SNAPSHOT")
  // paperweight.devBundle("com.example.paperfork", "1.21-R0.1-SNAPSHOT")
}

//def targetJavaVersion = 21
//java {
//    def javaVersion = JavaVersion.toVersion(targetJavaVersion)
//    sourceCompatibility = javaVersion
//    targetCompatibility = javaVersion
//    if (JavaVersion.current() < javaVersion) {
//        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
//    }
//}

tasks {
  compileJava {
    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    // See https://openjdk.java.net/jeps/247 for more information.
    options.release = 21
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
  }

  // Only relevant when going with option 2 above
  /*
  reobfJar {
    // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
    // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
    outputJar = layout.buildDirectory.file("libs/PaperweightTestPlugin-${project.version}.jar")
  }
   */
}

// Configure plugin.yml generation
// - name, version, and description are inherited from the Gradle project.
bukkitPluginYaml {
  main = "noppe.minecraft.arena.arenaplugin.Arena"
  load = BukkitPluginYaml.PluginLoadOrder.STARTUP
  authors.add("Noppe")
  apiVersion = "1.21"
}