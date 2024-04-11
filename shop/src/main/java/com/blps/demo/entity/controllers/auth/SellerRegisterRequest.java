package com.blps.demo.entity.controllers.auth;

import com.blps.demo.user.Role;

public record SellerRegisterRequest(
        String name,
        Integer passport,
        String email,
        String password
) {}
