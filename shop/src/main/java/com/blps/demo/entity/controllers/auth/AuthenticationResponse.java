package com.blps.demo.entity.controllers.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponse(
        @JsonProperty("user_id") Integer userId,
        @JsonProperty("access_token") String accessToken
) {}
