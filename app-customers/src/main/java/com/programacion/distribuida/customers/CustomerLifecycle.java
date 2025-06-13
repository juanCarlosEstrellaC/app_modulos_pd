package com.programacion.distribuida.customers;

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
public class CustomerLifecycle {

    @Inject
    @ConfigProperty(name = "consul.host", defaultValue = "127.0.0.1")
    String consulHost;

    @Inject
    @ConfigProperty(name = "consul.port", defaultValue = "8500")
    Integer consulPort;

    @Inject
    @ConfigProperty(name = "quarkus.http.port")
    Integer appPort;

    String serviceId;

    void init(@Observes StartupEvent event, Vertx vertx) throws Exception {
        System.out.println("Starting Customer service...");

        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consulPort);

        ConsulClient consulClient = ConsulClient.create(vertx, options);

        serviceId = UUID.randomUUID().toString();
        var ipAddress = InetAddress.getLocalHost();

        // Definir las etiquetas del servicio
        var tags = List.of(
                "traefik.enable=true",
                "traefik.http.routers.app-customers.rule=PathPrefix(`/app-customers`)",
                "traefik.http.routers.app-customers.middlewares=strip-prefix-customers",
                "traefik.http.middlewares.strip-prefix-customers.stripPrefix.prefixes=/app-customers"
        );

        // Configurar las opciones de verificación del servicio
        var checkOptions = new CheckOptions()
                //.setHttp("http://127.0.0.1:7070/ping")
                .setHttp(String.format("http://%s:%s/ping", ipAddress.getHostAddress(), appPort))
                .setInterval("10s")
                .setDeregisterAfter("20s");

        //--registro
        ServiceOptions serviceOptions = new ServiceOptions()
                .setName("app-customers")
                .setId(serviceId)
                .setAddress(ipAddress.getHostAddress())
                .setTags(tags)
                .setCheckOptions(checkOptions)
                .setPort(appPort);

        consulClient.registerServiceAndAwait(serviceOptions);
    }

    void stop(@Observes ShutdownEvent event, Vertx vertx) {
        System.out.println("Stopping Customer service...");

        ConsulClientOptions options = new ConsulClientOptions()
                .setHost(consulHost)
                .setPort(consulPort);
        ConsulClient consulClient = ConsulClient.create(vertx, options);

        consulClient.deregisterServiceAndAwait(serviceId);

    }
}
