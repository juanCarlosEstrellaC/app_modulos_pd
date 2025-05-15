plugins {
    id("java")
    //id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.example"
version = "unspecified"

repositories {
        mavenCentral()
}

dependencies {


    //CDI: Weld
    // https://mvnrepository.com/artifact/org.jboss.weld.se/weld-se-core
    //implementation("org.jboss.weld.se:weld-se-core:6.0.2.Final")
   // implementation("org.jboss.weld.servled:weld-servlet-core:6.0.2.Final")
    //implementation("org.jboss.weld.servlet:weld-servlet-core:6.0.2.Final")

    // https://mvnrepository.com/artifact/io.smallrye/jandex
    //implementation("io.smallrye:jandex:3.2.7")

    //REST: usando RESTEasy
    // https://mvnrepository.com/artifact/org.jboss.resteasy/resteasy-core
    implementation("org.jboss.resteasy:resteasy-core:6.2.12.Final")

    //Necesitamos un servidor

    implementation("org.jboss.resteasy:resteasy-undertow-cdi:6.2.12.Final")

    //JPA: Eclipse Link
    // https://mvnrepository.com/artifact/org.eclipse.persistence/eclipselink
    implementation("org.eclipse.persistence:eclipselink:4.0.6")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.5")
}

sourceSets {
    main {
        output.setResourcesDir( file("${buildDir}/classes/java/main") )
    }}

tasks.shadowJar{
 mergeServiceFiles()
}

tasks.jar{
    manifest {
        attributes("Main-Class" to "com.programacion.distribuida.Ejemplo03Main",
            "Class-Path" to configurations.runtimeClasspath
                .get()
                .joinToString(" "){
                    file->"${file.name}"
                })
    }
}