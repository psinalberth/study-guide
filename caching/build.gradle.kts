plugins {
    java
    `java-library`
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.awaitility:awaitility:4.3.0")
    testImplementation("org.mockito:mockito-core:5.19.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.19.0")
}

tasks.test {
    useJUnitPlatform()
}