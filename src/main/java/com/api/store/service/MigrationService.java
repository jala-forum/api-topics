package com.api.store.service;

import com.api.store.infra.database.mongodb.repositories.MongoIdeaRepository;
import com.api.store.infra.database.mongodb.repositories.MongoTopicRepository;
import com.api.store.infra.database.mongodb.repositories.MongoVoteRepository;
import com.api.store.infra.database.mysql.repositories.MysqlIdeaRepository;
import com.api.store.infra.database.mysql.repositories.MysqlTopicRepository;
import com.api.store.infra.database.mysql.repositories.MysqlVoteRepository;
import com.api.store.model.entities.mysql.Idea;
import com.api.store.model.entities.mysql.Topic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MigrationService {
    private final MongoTopicRepository mongoTopicRepository;
    private final MysqlTopicRepository mysqlTopicRepository;

    private final MongoIdeaRepository mongoIdeaRepository;
    private final MysqlIdeaRepository mysqlIdeaRepository;

    private final MongoVoteRepository mongoVoteRepository;
    private final MysqlVoteRepository mysqlVoteRepository;


    @Autowired
    public MigrationService(MongoTopicRepository mongoTopicRepository, MysqlTopicRepository mysqlTopicRepository, MongoIdeaRepository mongoIdeaRepository, MysqlIdeaRepository mysqlIdeaRepository, MongoVoteRepository mongoVoteRepository, MysqlVoteRepository mysqlVoteRepository) {
        this.mongoTopicRepository = mongoTopicRepository;
        this.mysqlTopicRepository = mysqlTopicRepository;
        this.mongoIdeaRepository = mongoIdeaRepository;
        this.mysqlIdeaRepository = mysqlIdeaRepository;
        this.mongoVoteRepository = mongoVoteRepository;
        this.mysqlVoteRepository = mysqlVoteRepository;
    }

    public void migrateMysqlToMongodb() {
        this.mongoTopicRepository.saveAll(this.convertTopic());
        this.mongoIdeaRepository.saveAll(this.convertIdea());
        this.mongoVoteRepository.saveAll(this.convertVote());
    }

    private List<com.api.store.model.entities.mongodb.Topic> convertTopic() {
        List<Topic> mysqlTopics = this.mysqlTopicRepository.findAll();

        List<com.api.store.model.entities.mongodb.Topic> mongodbTopics = new ArrayList<>();

        for (Topic topicSql : mysqlTopics) {
            com.api.store.model.entities.mongodb.Topic topicMongo = new com.api.store.model.entities.mongodb.Topic();
            BeanUtils.copyProperties(topicSql, topicMongo);
            topicMongo.setUserId(topicSql.getUser().getId().toString());

            mongodbTopics.add(topicMongo);
        }

        return mongodbTopics;
    }

    private List<com.api.store.model.entities.mongodb.Idea> convertIdea() {
        List<Idea> mysqlIdeas = this.mysqlIdeaRepository.findAll();

        List<com.api.store.model.entities.mongodb.Idea> mongodbIdeas = new ArrayList<>();

        for (Idea ideaSql : mysqlIdeas) {
            com.api.store.model.entities.mongodb.Idea ideaMongo = new com.api.store.model.entities.mongodb.Idea();
            BeanUtils.copyProperties(ideaSql, ideaMongo);
            ideaMongo.setUserId(ideaSql.getUser().getId().toString());
            ideaMongo.setTopicId(ideaSql.getTopic().getId().toString());

            mongodbIdeas.add(ideaMongo);
        }

        return mongodbIdeas;
    }

    private List<com.api.store.model.entities.mongodb.Vote> convertVote() {
        List<com.api.store.model.entities.mysql.Vote> mysqlVotes = this.mysqlVoteRepository.findAll();

        List<com.api.store.model.entities.mongodb.Vote> mongodbVotes = new ArrayList<>();

        for (com.api.store.model.entities.mysql.Vote voteSql : mysqlVotes) {
            com.api.store.model.entities.mongodb.Vote voteMongo = new com.api.store.model.entities.mongodb.Vote();
            BeanUtils.copyProperties(voteSql, voteMongo);
            voteMongo.setUserId(voteSql.getUser().getId().toString());
            voteMongo.setIdeaId(voteSql.getIdea().getId().toString());

            mongodbVotes.add(voteMongo);
        }

        return mongodbVotes;
    }
}
