package com.programacion.distribuida.customers.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path( "/ping")
public class PingRet {

    @GET
    public String ping() {
        return "pong";
    }
}
