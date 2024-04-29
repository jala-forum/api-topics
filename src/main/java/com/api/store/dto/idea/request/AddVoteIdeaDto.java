package com.api.store.dto.idea.request;

import org.hibernate.validator.constraints.Length;

public record AddVoteIdeaDto(
        @Length(max=40)
        String ideaId
) {}
