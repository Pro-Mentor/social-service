package com.github.promentor.web.impl;

import com.github.promentor.data.domain.PostDAO;
import com.github.promentor.data.repository.PostRepository;
import com.github.promentor.mappers.PostMapper;
import com.github.promentor.web.dto.PostCreateDTO;
import com.github.promentor.web.dto.PostGetDTO;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostResourcesImpl {

    private final PostMapper postMapper;

    private final PostRepository postRepository;

    public PostResourcesImpl(PostMapper postMapper, PostRepository postRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    public Uni<String> createdPost(PostCreateDTO postCreateDTO) {
        Log.debug("reserved: " + postCreateDTO);

        return this.postRepository.persist(this.postMapper.toPostDAO(postCreateDTO))
                .onItem()
                .transform(postDAO -> postDAO.id.toString());
    }

}
