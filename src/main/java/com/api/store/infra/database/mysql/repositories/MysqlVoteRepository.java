package com.api.store.infra.database.mysql.repositories;

import com.api.store.model.entities.mysql.Idea;
import com.api.store.model.entities.mysql.User;
import com.api.store.model.entities.mysql.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MysqlVoteRepository extends JpaRepository<Vote, UUID> {
    @Query
    Optional<Vote> findByUser(User user);

    @Query
    Set<Vote> findAllByUser(User user);

    @Query
    Set<Vote> findAllByIdea(Idea idea);
}
