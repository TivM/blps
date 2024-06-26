package com.blps.demo.entity.controllers.item;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemWithStatus(
        @JsonProperty("id") Integer id,
        @JsonProperty("status") String status
) {}
