package com.github.promentor.data.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection="Modes")
public class ModeDAO {

    public ObjectId id;
    public String key;

    public ModeDAO() {
    }

    public ModeDAO(ObjectId objectId, String key) {
        this.id = objectId;
        this.key = key;
    }
}
