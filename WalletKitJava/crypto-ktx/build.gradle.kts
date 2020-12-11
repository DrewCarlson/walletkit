import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    `maven-publish`
}

apply(from = "../gradle/publish.gradle")

val deps: Map<*, *> by rootProject.extra

kotlin {
    dependencies {
        api(project(":crypto"))
        implementation(deps["coroutinesCore"]!!)
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["kotlin"])
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
