package com.github.promentor.web.impl;

import com.github.promentor.data.domain.PostDAO;
import com.github.promentor.data.repository.PostRepository;
import com.github.promentor.data.repository.UserRepository;
import com.github.promentor.exceptions.ErrorCode;
import com.github.promentor.exceptions.custom.InvalidUUID;
import com.github.promentor.exceptions.custom.NotAuthorizeException;
import com.github.promentor.exceptions.custom.NotFoundException;
import com.github.promentor.mappers.PostMapper;
import com.github.promentor.utils.IdConverter;
import com.github.promentor.web.dto.PostCreateDTO;
import com.github.promentor.web.dto.PostGetDTO;
import com.github.promentor.web.dto.PostUpdateDTO;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.security.Principal;
import java.util.List;

@ApplicationScoped
public class PostResourcesImpl {

    private final PostMapper postMapper;

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostResourcesImpl(PostMapper postMapper, PostRepository postRepository, UserRepository userRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * get the post by the post id
     * @param postId id of the post
     * @return the requested post As Uni
     * @throws NotFoundException if the post not available with given id
     * @throws InvalidUUID if the give id is not a valid id
     */
    public Uni<PostGetDTO> getPostById(String postId) {
        Log.debug("reserved request to get the post by id: " + postId);

        return postRepository
                .findById(IdConverter.getObjectId(postId))
                .onItem().ifNull().failWith(new NotFoundException(ErrorCode.POST_NOT_FOUND))
                .onItem().transform(this.postMapper::toPostGetDTO);

    }

    public Uni<List<PostGetDTO>> getAllPost(int pageIndex, int pageSize) {

        return postRepository
                .findAll(Sort.by("updatedAt").descending())
                .page(pageIndex, pageSize)
                .stream().onItem().transform(this.postMapper::toPostGetDTO)
                .collect().asList();

    }

    /**
     * create a post
     * @param postCreateDTO details want to create a post
     * @return id of the created object
     */
    public Uni<String> createdPost(PostCreateDTO postCreateDTO, Principal principal) {
        Log.debug("reserved: " + postCreateDTO);

        PostDAO post = this.postMapper.toPostDAO(postCreateDTO);
        post.createdBy = principal.getName();

        return this.userRepository.findByUsername(principal.getName())
                .onItem().transformToUni(userDAO -> {
                    userDAO.ifPresent(dao -> post.owner = dao);

                    return this.postRepository.persist(post)
                            .onItem()
                            .transform(postDAO -> postDAO.id.toString());
                });

    }

    /**
     * update the post by id
     * @param postId id of the post
     * @param postUpdateDTO details want to update
     * @return updated post
     */
    public Uni<PostGetDTO> updatePostById(String postId, PostUpdateDTO postUpdateDTO, Principal principal) {
        Log.debug("reserved request to update: " + postId + " as: " + postUpdateDTO);

        return this.postRepository
                .findById(IdConverter.getObjectId(postId))
                .onItem().ifNull().failWith(new NotFoundException(ErrorCode.POST_NOT_FOUND))
                .onItem()
                .transformToUni(postDAO -> {
                    Log.debug("available post: " + postDAO);

                    if (!postDAO.createdBy.equals(principal.getName())) {
                        return Uni.createFrom().failure(new NotAuthorizeException(ErrorCode.NOT_POST_OWNER));
                    }

                    this.postMapper.merge(postDAO, postUpdateDTO);

                    Log.debug("updated post: " + postDAO);
                    return postRepository.update(postDAO);
                }).onItem().transform(this.postMapper::toPostGetDTO);
    }

    public Uni<Void> deletePostById(String postId, Principal principal) {
        Log.debug("reserved request to delete: " + postId );

        return this.postRepository
                .findById(IdConverter.getObjectId(postId))
                .onItem().ifNull().failWith(new NotFoundException(ErrorCode.POST_NOT_FOUND))
                .onItem()
                .transformToUni(postDAO -> {
                    Log.debug("available post: " + postDAO);

                    if (!postDAO.createdBy.equals(principal.getName())) {
                        return Uni.createFrom().failure(new NotAuthorizeException(ErrorCode.NOT_POST_OWNER));
                    }

                    return postRepository.delete(postDAO);
                });
    }

}
