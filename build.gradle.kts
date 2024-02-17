plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "personal.project"
version = "1.0-SNAPSHOT"


application {
    mainClass.set("dominion.DominionApp")
}

repositories {
    mavenCentral()
}

dependencies {
    // Logging dependency
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.22.1")
    implementation("org.jetbrains:annotations:24.0.0")


    // Graphing
    // https://mvnrepository.com/artifact/org.jfree/jfreechart
    implementation("org.jfree:jfreechart:1.5.4")
    // https://mvnrepository.com/artifact/org.openjfx/javafx-controls
    implementation("org.openjfx:javafx-controls:23-ea+3")
    // https://mvnrepository.com/artifact/org.openjfx/javafx-fxml
    implementation("org.openjfx:javafx-fxml:23-ea+3")


    // Testing dependencies
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.10.0")

}

javafx {
    modules("javafx.controls", "javafx.fxml")
}

logging.captureStandardOutput(LogLevel.ERROR)

tasks.test {
    useJUnitPlatform()
}

