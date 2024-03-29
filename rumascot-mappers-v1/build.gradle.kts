plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":rumascot-api-v1-jackson"))
    implementation(project(":rumascot-common"))

    testImplementation(kotlin("test-junit"))
}