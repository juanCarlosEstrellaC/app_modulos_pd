plugins {
    id("java")
    id("war")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    //Dependenica el API de CDI, JSON-B
    // https://mvnrepository.com/artifact/jakarta.enterprise/jakarta.enterprise.cdi-api
    compileOnly("jakarta.enterprise:jakarta.enterprise.cdi-api:4.1.0")
    //Dependencia el API JAX-RS
    // https://mvnrepository.com/artifact/jakarta.ws.rs/jakarta.ws.rs-api
    compileOnly("jakarta.ws.rs:jakarta.ws.rs-api:4.0.0")
    //compileOnly para que solo se ejecuten aqui y no me intervenga
    //con las dependencias del servidor

}

tasks.test {
    useJUnitPlatform()
}