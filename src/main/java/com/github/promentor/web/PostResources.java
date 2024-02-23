package com.github.promentor.web;

import com.github.promentor.exceptions.ErrorMessage;
import com.github.promentor.web.dto.PostCreateDTO;
import com.github.promentor.web.dto.PostGetDTO;
import com.github.promentor.web.dto.PostUpdateDTO;
import com.github.promentor.web.impl.PostResourcesImpl;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.net.URI;

@Tag(name = "Post", description = "Describe the functionalities related to Post")
@Path("/posts")
@ApplicationScoped
//@Authenticated
public class PostResources {

    private final PostResourcesImpl postResources;

    public PostResources(PostResourcesImpl postResources) {
        this.postResources = postResources;
    }

    @GET
    @Path("/{post-id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getPostById", summary = "get a post", description = "get a post by post id")
    @Parameter(name = "post-id", description = "unique id of the post", required = true)
    @APIResponses( value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PostGetDTO.class))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid Request",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class))
            )
    })
    public Uni<Response> getPostById(@PathParam("post-id") String postId) {
        Log.info("reserved request to get the post");
        Log.debug("reserved request to get the post with id: " + postId);

        return this.postResources
                .getPostById(postId)
                .map(postGetDTO -> Response.ok(postGetDTO).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    @Operation(operationId = "createPost", summary = "create a post", description = "create a post")
    @APIResponses( value = {
            @APIResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid Request",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class))
            ),
            @APIResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class))
            ),

    })
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

    @PUT
    @Path("/{post-id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    @Parameter(name = "post-id", description = "unique id of the post", required = true)
    @Operation(operationId = "updatePost", summary = "update a post", description = "update a post")
    @APIResponses( value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PostGetDTO.class))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid Request",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class))
            ),
            @APIResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content =  @Content(
                            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ErrorMessage.class))
            ),
    })
    public Uni<Response> updatePost(@PathParam("post-id") String postId, @Valid PostUpdateDTO postUpdateDTO) {
        Log.info("reserved request to update post: " + postId);
        Log.debug("request reserved: " + postUpdateDTO);

        return this.postResources
                .updatePostById(postId, postUpdateDTO)
                .onItem()
                .transform(post -> {
                    Log.info("updated post with id: " + postId);
                    Log.debug("updated post: " + post);
                    return Response.ok(post).build();
                });

    }

}
