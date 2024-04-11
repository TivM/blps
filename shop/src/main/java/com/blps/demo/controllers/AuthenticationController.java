package com.blps.demo.controllers;

import com.blps.demo.entity.controllers.auth.*;
import com.blps.demo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/clientRegister")
    public ResponseEntity<AuthenticationResponse> clientRegister(
            @RequestBody ClientRegisterRequest request
    ) {
        return ResponseEntity.ok(service.clientRegister(request));
    }

    @PostMapping("/sellerRegister")
    public ResponseEntity<AuthenticationResponse> sellerRegister(
            @RequestBody SellerRegisterRequest request
    ) {
        return ResponseEntity.ok(service.sellerRegister(request));
    }

    @PostMapping("/pickupEmployeeRegister")
    public ResponseEntity<AuthenticationResponse> pickupEmployeeRegister(
            @RequestBody PickupPointEmployeeRequest request
    ) {
        return ResponseEntity.ok(service.pickupPointEmployeeRegister(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
