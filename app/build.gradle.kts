plugins {
    id("com.github.ben-manes.versions") version "0.53.0"
    checkstyle
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

application {
    mainClass = "hexlet.code.App"
}

dependencies {
    implementation("info.picocli:picocli:4.7.7")
}