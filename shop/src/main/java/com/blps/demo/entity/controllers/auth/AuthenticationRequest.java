package com.blps.demo.entity.controllers.auth;

public record AuthenticationRequest(
    String email,
    String password
){}

