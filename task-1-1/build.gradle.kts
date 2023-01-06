val jupiterVersion: String by project

plugins {
    kotlin("jvm")
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:$jupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}