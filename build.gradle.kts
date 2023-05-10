import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaVersion: String by project
val jupiterVersion: String by project

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm")
}

group = "ru.otus.goppeav.kotlin-tasks"
version = "1.0"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        dependencies {
            testImplementation("org.junit.jupiter:junit-jupiter-api:$jupiterVersion")
            testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")
        }
    }


}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = javaVersion
}