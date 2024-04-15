package com.notifier.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemWithStatus(
        @JsonProperty("id") Integer id,
        @JsonProperty("status") String status
) {}
