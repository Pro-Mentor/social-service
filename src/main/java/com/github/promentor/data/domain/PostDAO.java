package com.github.promentor.data.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection="post")
public class PostDAO {

    public ObjectId id;
    public String description;

    public PostDAO() {
    }

    public PostDAO(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PostDAO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
