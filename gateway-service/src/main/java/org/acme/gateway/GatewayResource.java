package org.acme.gateway;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.annotation.security.RolesAllowed;

@Path("/gateway")
public class GatewayResource {

    private final Template index;

    public GatewayResource(Template index) {
        this.index = index;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("user")
    public TemplateInstance get() {
        return index.data("name", "Gateway");
    }
}
