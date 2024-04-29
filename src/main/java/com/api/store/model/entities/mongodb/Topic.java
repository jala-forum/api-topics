package com.api.store.model.entities.mongodb;

import com.api.store.model.entities.mysql.Idea;
import com.api.store.model.entities.mysql.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Setter
@Getter
@Document("topic")
public class Topic {
    @MongoId
    private String id;

    private String userId;

    private String title;

    private String description;
}
