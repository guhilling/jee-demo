package de.hilling.cdi.sampleapp.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/ping")
@ApplicationScoped
public class ConnectionTestResource {

    @Inject
    @ConfigProperty(name = "cdi-test-demo.pong")
    private String pong;

    @Path("/")
    @GET
    public String ping() {
        return pong;
    }

}
