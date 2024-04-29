package com.api.store.dto.topic.request;

import org.hibernate.validator.constraints.Length;

public record AddTopicRequestDto (
    @Length(max = 100, min = 5)
    String title,
    @Length(max = 400, min = 5)
    String description
){}
