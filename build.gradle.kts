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

    implementation("org.apache.commons:commons-lang3:3.11")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.2.0.RC2")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.2.0.RC2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
