package com.github.promentor.data.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection="post")
public class PostDAO extends AuditableEntity {

    public ObjectId id;
    public String description;
    public String imageUrl;



    public PostDAO() {
        super();
    }

    public PostDAO(String description, String imageUrl) {
        super();
        this.description = description;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "PostDAO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
