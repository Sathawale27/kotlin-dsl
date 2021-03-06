import org.gradle.api.tasks.bundling.Jar

plugins {
    `java-gradle-plugin`
    `maven-publish`
    groovy
}

group = "my"

version = "1.0"

gradlePlugin {
    plugins {
        register("documentation") {
            id = "my.documentation"
            implementationClass = "my.DocumentationPlugin"
        }
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    classifier = "sources"
    from(sourceSets["main"].allSource)
}

publishing {
    publications {
        register("mavenSources", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
    repositories {
        maven(url = "build/repository")
    }
}
