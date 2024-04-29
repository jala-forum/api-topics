package com.api.store.model.entities.mongodb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Setter
@Getter
@Document("idea")
public class Idea {
    @MongoId
    private String id;

    private String text;

    private String userId;

    private String topicId;
}
