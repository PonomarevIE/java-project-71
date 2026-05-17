plugins {
    id("com.github.ben-manes.versions") version "0.53.0"
    id("io.freefair.lombok") version "9.5.0"
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
    implementation("tools.jackson.core:jackson-core:3.1.3")
    implementation("tools.jackson.core:jackson-databind:3.1.3")
}