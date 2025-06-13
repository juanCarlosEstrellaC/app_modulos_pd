package com.programacio.distribuida.books;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.consul.ConsulClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookLifecycle {

    @Inject
    @ConfigProperty(name = "consul.host", defaultValue = "localhost")
    String consulHost;

    @Inject
    @ConfigProperty(name = "consul.port", defaultValue = "8500")
    Integer consulPort;

    @Inject
    @ConfigProperty(name = "quarkus.http.port")
    Integer appPort;

    String serviceId;

    void init(@Observes StartupEvent event, Vertx vertx) throws Exception{
        System.out.println("Starting Book Service...");
        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consulPort);
        ConsulClient consulClient = ConsulClient.create(vertx, options);

        serviceId = UUID.randomUUID().toString();
        var ipAddress = InetAddress.getLocalHost();
        System.out.printf("*************IP Address: %s\n", ipAddress.getHostAddress());

        // Definir las etiquetas del servicio
        var tags = List.of(
                "traefik.enable=true",
                "traefik.http.routers.app-books.rule=PathPrefix(`/app-books`)",
                "traefik.http.routers.app-books.middlewares=strip-prefix-books",
                "traefik.http.middlewares.strip-prefix-books.stripPrefix.prefixes=/app-books"
        );

        // Configurar las opciones de verificación del servicio
        var checkOptions = new CheckOptions()
                //.setHttp("http://127.0.0.1:9090/ping")
                .setHttp(String.format("http://%s:%s/ping", ipAddress.getHostAddress(), appPort))
                .setInterval("10s")
                .setDeregisterAfter("20s");

        // Registrar el servicio en Consul
        ServiceOptions serviceOptions = new ServiceOptions()
                .setId(serviceId)
                .setName("app-books")
                .setAddress("127.0.0.1")
                .setTags(tags)
                .setCheckOptions(checkOptions)
                .setAddress(ipAddress.getHostAddress())
                .setPort(appPort);

        consulClient.registerServiceAndAwait(serviceOptions);
    }

    void stop(@Observes ShutdownEvent event, Vertx vertx) {
        System.out.println("Parando Book Service...");
        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consulPort);
        ConsulClient consulClient = ConsulClient.create(vertx, options);
        consulClient.deregisterServiceAndAwait(serviceId);
    }
}
