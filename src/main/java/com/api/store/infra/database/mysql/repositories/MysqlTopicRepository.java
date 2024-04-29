package com.api.store.infra.database.mysql.repositories;

import com.api.store.model.entities.mysql.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MysqlTopicRepository extends JpaRepository<Topic, UUID> { }
