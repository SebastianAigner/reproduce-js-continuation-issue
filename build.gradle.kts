val kotlinVersion = "1.4-M2"
val serializationVersion = "0.20.0-1.4-M2"
val ktorVersion = "1.3.2-1.4-M2"


plugins {
    kotlin("multiplatform") version "1.4-M2"
    kotlin("plugin.serialization") version "1.4-M2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
    mavenCentral()
    jcenter()
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    js(IR) {
        browser { }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(npm("text-encoding"))
                implementation(npm("abort-controller"))
                implementation(npm("bufferutil"))
                implementation(npm("utf-8-validate"))
                implementation(npm("fs"))
            }
        }
    }
}