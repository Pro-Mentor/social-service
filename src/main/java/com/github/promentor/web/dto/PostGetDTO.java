package com.github.promentor.web.dto;

import java.time.Instant;

public record PostGetDTO(
        String id,
        String description,
        String imageUrl,
        Instant createdAt,
        Instant updatedAt,
        String createdBy,
        String createdById,
        UserGetDTO owner
) {
}
