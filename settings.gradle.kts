pluginManagement {
    val kotlinJvmVersion: String by settings
    val kotestVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinJvmVersion
        kotlin("multiplatform") version kotlinJvmVersion
        id("io.kotest.multiplatform") version kotestVersion apply false
    }
}

rootProject.name = "goppeav-kotlin-2022-12"

include("app")