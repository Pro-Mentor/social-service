package com.github.promentor.data.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection="PostLike")
public class PostLike {

    public ObjectId id;
    public ObjectId postId;
    public String username;

    public PostLike() {

    }

    public PostLike(ObjectId postId, String username) {
        this.postId = postId;
        this.username = username;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", postId=" + postId +
                ", username='" + username + '\'' +
                '}';
    }
}
