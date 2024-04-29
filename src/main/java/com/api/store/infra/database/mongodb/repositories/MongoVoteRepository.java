package com.api.store.infra.database.mongodb.repositories;

import com.api.store.model.entities.mongodb.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MongoVoteRepository extends MongoRepository<Vote, String> {
    @Query
    Set<Vote> findAllByUserId(String id);

    @Query
    List<Vote> findAllByIdeaId(String id);
}
