package com.api.store.model.entities.mongodb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Setter
@Getter
@Document("vote")
public class Vote {
    @MongoId
    private String id;

    private String userId;
    private String ideaId;
}
