rootProject.name = "otus-kotlin-goppeav"

pluginManagement {
    val kotlinVersion: String by settings
    val kotestVersion: String by settings
    val openapiVersion: String by settings
    val springframeworkBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val pluginSpringVersion: String by settings
    val pluginJpa: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion apply false
        id("io.kotest.multiplatform") version kotestVersion apply false
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false

        id("org.springframework.boot") version springframeworkBootVersion apply false
        id("io.spring.dependency-management") version springDependencyManagementVersion apply false
        kotlin("plugin.spring") version pluginSpringVersion apply false
        kotlin("plugin.jpa") version pluginJpa apply false
    }
}


include("rumascot-api-log1")

include("rumascot-api-v1-jackson")
include("rumascot-api-v2-kmp")

include("rumascot-app-spring")
include("rumascot-app-common")

include("rumascot-biz")

include("rumascot-common")

include("rumascot-lib-logging-common")
include("rumascot-lib-logging-logback")

include("rumascot-mappers-log1")

include("rumascot-mappers-v1")
include("rumascot-mappers-v2")

include("rumascot-stubs")