package com.api.store.dto.idea.request;

import jakarta.validation.constraints.Max;
import org.hibernate.validator.constraints.Length;

public record AddIdeaRequestDto (
    @Length(max = 5000, min=5)
    String text,
    String topicId
){}
