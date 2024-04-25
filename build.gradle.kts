plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "8.6"
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

    // Testing dependencies
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.10.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("competitionJar") {
    group = "mascompetition"
    manifest {
        attributes["Main-Class"] = "competition.CompetitionLauncher"
    }
    archiveBaseName.set("mas-engine")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get())
}
