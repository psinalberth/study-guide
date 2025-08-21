plugins {
    java
}

group = "com.github.psinalberth"
version = "0.0.1"

repositories {
    mavenCentral()
}

subprojects {

    apply {
        plugin("java")
        plugin("java-library")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.apache.logging.log4j:log4j-api:2.25.1")
        implementation("org.apache.logging.log4j:log4j-core:2.25.1")
        implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.25.1")
        testImplementation(platform("org.junit:junit-bom:5.13.4"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.assertj:assertj-core:3.27.4")
        testImplementation("org.mockito:mockito-core:5.19.0")
        testImplementation("org.mockito:mockito-junit-jupiter:5.19.0")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

