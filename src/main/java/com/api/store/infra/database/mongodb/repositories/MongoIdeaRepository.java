package com.api.store.infra.database.mongodb.repositories;

import com.api.store.model.entities.mongodb.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MongoIdeaRepository extends MongoRepository<Idea, String> {
    @Query
    Set<Idea> findAllByTopicId(String id);
}
