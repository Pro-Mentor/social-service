package com.github.promentor.web;

import com.github.promentor.web.dto.PostCreateDTO;
import com.github.promentor.web.impl.PostResourcesImpl;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponseSchema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.net.URI;

@Tag(name = "Post", description = "Describe the functionalities related to Post")
@Path("/posts")
@ApplicationScoped
public class PostResources {

    private final PostResourcesImpl postResources;

    public PostResources(PostResourcesImpl postResources) {
        this.postResources = postResources;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "create a post", description = "create a post")
    @APIResponseSchema(value = String.class)
    public Uni<Response> createdPost(@Valid PostCreateDTO postCreateDTO) {
        Log.info("reserved request to create post");
        Log.debug("request reserved: " + postCreateDTO);

        return this.postResources
                .createdPost(postCreateDTO)
                .onItem()
                .transform(id -> {
                    Log.info("create post with id: " + id);
                    return Response.created(URI.create("/posts/" + id)).build();
                });
    }

}
