val jupiterVersion: String by project

plugins {
    kotlin("jvm")

    application
}



dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter-api:$jupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

application {
    mainClass.set("ru.otus.goppeav.main.MainKt")
}