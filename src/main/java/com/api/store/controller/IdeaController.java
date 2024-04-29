package com.api.store.controller;

import com.api.store.dto.idea.request.AddIdeaRequestDto;
import com.api.store.dto.idea.request.AddVoteIdeaDto;
import com.api.store.model.entities.mongodb.Idea;
import com.api.store.model.entities.mongodb.Vote;
import com.api.store.service.IdeaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/idea")
public class IdeaController {
    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping
    public void addIdea(@RequestBody @Valid AddIdeaRequestDto data, HttpServletRequest request) {
        String text = data.text();
        String topicId = data.topicId();
        String userId = (String) request.getAttribute("userId");

        this.ideaService.add(text, topicId, userId);
    };

    @RequestMapping("/{id}")
    public Set<Idea> getIdeaByTopicId(@PathVariable String id) {
        return this.ideaService.getIdeaByTopicId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteIdeaById(@PathVariable String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");

        this.ideaService.deleteById(id, userId);
    }

    @PostMapping("/vote")
    public void addVoteIdea(@RequestBody @Valid AddVoteIdeaDto data, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");

        this.ideaService.addVote(data.ideaId(), userId);
    }

    @DeleteMapping("/vote/{id}")
    public void deleteVoteIdea(@PathVariable String id, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");

        this.ideaService.deleteVoteById(id, userId);
    }

    @RequestMapping("/vote/{ideaId}")
    public List<Vote> getVoteByTopic(@PathVariable String ideaId) {
        return this.ideaService.getVoteByIdeaId(ideaId);
    }
}
