package com.github.promentor.web;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Post", description = "Describe the functionalities related to Post")
@Path("/post")
@ApplicationScoped
public class PostResources {

    @GET
    public Uni<Response> createdPost() {
        return Uni.createFrom().nullItem();
    }

}
