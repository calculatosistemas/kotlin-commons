import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm") version "1.3.72"
}

group = "br.com.calculato"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    compile("org.apache.commons:commons-lang3:3.11")
}
