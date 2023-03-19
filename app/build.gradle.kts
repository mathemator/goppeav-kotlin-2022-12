import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm")

    application
}

repositories {
    maven {
        url = uri("https://github.com/sndyuk/maven")
    }
}

kotlin {

    val kotestVersion: String by project
    val coroutinesVersion: String by project

    dependencies {
        implementation(kotlin("stdlib-jdk8"))

        implementation("org.slf4j:slf4j-api:1.7.25")
        implementation("ch.qos.logback:logback-classic:1.1.7")
        implementation("ch.qos.logback:logback-core:1.1.7")
        implementation("ch.qos.logback.contrib:logback-jackson:0.1.5")
        implementation("com.sndyuk:logback-more-appenders:1.3.1")
        implementation("org.fluentd:fluent-logger:0.3.4")
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

        implementation("io.kotest:kotest-framework-engine:$kotestVersion")
        implementation("io.kotest:kotest-framework-datatest:$kotestVersion")
        implementation("io.kotest:kotest-assertions-core:$kotestVersion")
        implementation("io.kotest:kotest-property:$kotestVersion")
    }

}

tasks.jar {
    manifest.attributes["Main-Class"] = "ru.otus.goppeav.main.MainKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks {
    withType<Test>().configureEach {
        useJUnitPlatform()
        filter {
            isFailOnNoMatchingTests = false
        }
        testLogging {
            showExceptions = true
            showStandardStreams = true
            events = setOf(TestLogEvent.FAILED, TestLogEvent.PASSED)
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}
