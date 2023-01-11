pluginManagement {
    val kotlinJvmVersion: String by settings
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinJvmVersion
    }
}

rootProject.name = "goppeav-kotlin-2022-12"

include("task-1-1")
