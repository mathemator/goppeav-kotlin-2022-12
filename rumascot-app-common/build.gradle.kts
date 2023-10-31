plugins {
    kotlin("multiplatform")
}
kotlin {
    jvm {}

    sourceSets {
        val logbackVersion: String by project

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api(project(":rumascot-common"))
                api(project(":rumascot-biz"))

                // v2 api
                api(project(":rumascot-api-v2-kmp"))
                api(project(":rumascot-mappers-v2"))

                // biz
                api(project(":rumascot-biz"))

                // logging
                api(project(":rumascot-lib-logging-common"))
                api(project(":rumascot-lib-logging-logback"))
                api(project(":rumascot-mappers-log1"))
                api(project(":rumascot-api-log1"))

                // Stubs
//                implementation(project(":rumascot-stubs"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api("ch.qos.logback:logback-classic:$logbackVersion")

                // transport models
                api(project(":rumascot-api-v1-jackson"))
                api(project(":rumascot-mappers-v1"))

                // logs
                api(project(":rumascot-lib-logging-logback"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}
