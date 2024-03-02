package com.github.promentor.mappers;

import com.github.promentor.data.domain.PostComment;
import com.github.promentor.web.dto.CommentGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PostCommentMapper {

    @Mapping(target = "id", expression = "java(postComment.id.toString())")
    @Mapping(target = "postId", expression = "java(postComment.postId.toString())")
    CommentGetDTO toCommentGetDTO(PostComment postComment);

}
