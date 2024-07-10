plugins {
    kotlin("jvm") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "8.0.0"
}

group = "io.github.sunshinewzy.sunnytangducodegeneration"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba:easyexcel:4.0.1")
    implementation("org.apache.logging.log4j:log4j-api:2.21.1")
    implementation("org.apache.logging.log4j:log4j-core:2.21.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.21.1")
    
    testImplementation(kotlin("test"))
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "io.github.sunshinewzy.sunnytangducodegeneration.SunnyTangDuCodeGeneration"
    }
}

tasks.shadowJar {
    archiveClassifier = ""
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}