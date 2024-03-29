plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "mascompetition"
version = "1.0"


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

    // Loading Configuration
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.apiguardian:apiguardian-api:1.1.0")

    // https://mvnrepository.com/artifact/com.google.errorprone/error_prone_annotations
    implementation("com.google.errorprone:error_prone_annotations:2.10.0")
    // Graphing
    // https://mvnrepository.com/artifact/org.jfree/jfreechart
    implementation("org.jfree:jfreechart:1.5.4")
    // https://mvnrepository.com/artifact/org.openjfx/javafx-controls
    implementation("org.openjfx:javafx-controls:23-ea+3")
    // https://mvnrepository.com/artifact/org.openjfx/javafx-fxml
    implementation("org.openjfx:javafx-fxml:23-ea+3")
    // https://mvnrepository.com/artifact/org.openjfx/javafx-swing
    implementation("org.openjfx:javafx-swing:23-ea+3")


    // Testing dependencies
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.10.0")

}

javafx {
    modules("javafx.controls", "javafx.fxml", "javafx.swing")
}

tasks.test {
    useJUnitPlatform()
}

