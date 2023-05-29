rootProject.name = "otus-kotlin-goppeav"

pluginManagement {
    val kotlinVersion: String by settings
    val kotestVersion: String by settings
    val openapiVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion apply false
        id("io.kotest.multiplatform") version kotestVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false
    }
}

include("rumascot-api-v1-jackson")
include("rumascot-api-v2-kmp")

include("rumascot-common")
include("rumascot-mappers-v1")
include("rumascot-mappers-v2")
