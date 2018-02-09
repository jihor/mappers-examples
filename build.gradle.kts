buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.21")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.3")
    }
}

repositories {
    jcenter()
}

plugins {
    java
    idea
    id("org.jetbrains.kotlin.jvm") version "1.2.21"
    // see settings.gradle and https://github.com/junit-team/junit5/issues/768
    id("org.junit.platform.gradle.plugin") version "1.0.3"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_9
    targetCompatibility = JavaVersion.VERSION_1_9
}

val generatedSourcesPath = file("out/production/classes/generated")

java.sourceSets["main"].java.srcDir(generatedSourcesPath)

idea {
    module {
        generatedSourceDirs.add(generatedSourcesPath)
    }
}

dependencies {
    compile("org.projectlombok:lombok:1.16.20")

    /** Dozer */
    compile("net.sf.dozer:dozer:5.5.1")
    compile("io.craftsman:dozer-jdk8-support:1.0.3")
    // unusable until https://github.com/DozerMapper/dozer/pull/546 is merged
    // compile("com.github.dozermapper:dozer-core:6.1.0")

    /** Orika */
    compile("ma.glasnost.orika:orika-core:1.5.2")

    /** Selma */
    compileOnly("fr.xebia.extras:selma-processor:1.0")
// only required in some mapping cases or when Selma.builder is used
//     compile("fr.xebia.extras:selma:1.0")

    /** MapStruct */
    compileOnly("org.mapstruct:mapstruct-processor:1.2.0.Final")
// only required in some mapping cases or when Selma.builder is used
    compile("org.mapstruct:mapstruct-jdk8:1.2.0.Final")

    /** ---------*/

    compile("one.util:streamex:0.6.6")

    // Tests
    testCompile("org.jetbrains.kotlin:kotlin-test:1.2.21")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")

    // Logging
    compile("org.slf4j:slf4j-api:1.7.25")
    testCompile("io.github.microutils:kotlin-logging:1.4.9")
    testRuntime("org.slf4j:jcl-over-slf4j:1.7.25")
    testRuntime("javax.xml.bind:jaxb-api:2.3.0")
    testRuntime("com.sun.xml.bind:jaxb-core:2.3.0")
    testRuntime("com.sun.xml.bind:jaxb-impl:2.3.0")
    testRuntime("org.apache.logging.log4j:log4j-slf4j-impl:2.10.0")
    testRuntime("com.fasterxml.jackson.core:jackson-databind:2.9.4")
    testRuntime("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.4")
}

// No HTML reports yet, see https://stackoverflow.com/questions/39444908/how-to-create-an-html-report-for-junit-5-tests
// Support is coming in Gradle 4.6, see: https://github.com/gradle/gradle/issues/1037