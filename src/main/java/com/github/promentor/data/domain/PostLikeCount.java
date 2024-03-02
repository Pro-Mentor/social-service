package com.github.promentor.data.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@MongoEntity(collection="PostLikeCount")
public class PostLikeCount {

    @BsonId
    public ObjectId id;
    public int count;

    public PostLikeCount() {

    }

    public PostLikeCount(ObjectId postId) {
        this.id = postId;
        this.count = 1;
    }

    public void addLike() {
        this.count++;
    }

    public void removeLike() {
        this.count--;
    }

}
