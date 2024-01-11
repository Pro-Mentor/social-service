package com.github.promentor.mappers;

import com.github.promentor.data.domain.PostDAO;
import com.github.promentor.web.dto.PostCreateDTO;
import com.github.promentor.web.dto.PostGetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PostMapper {

    PostDAO toPostDAO(PostCreateDTO postCreateDTO);

    @Mapping(target = "id", expression = "java(postDAO.id.toString())")
    PostGetDTO toPostGetDTO(PostDAO postDAO);

}
