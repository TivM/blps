package com.blps.demo.entity.controllers.auth;

import com.blps.demo.user.Role;

public record ClientRegisterRequest(
        String name,
        Integer age,
        String address,
        String email,
        String password
) {}
