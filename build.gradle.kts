plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.7.1")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useTestNG()
}