package com.blps.demo.services;

import com.blps.demo.entity.controllers.auth.*;

public interface AuthenticationService {
    AuthenticationResponse clientRegister(ClientRegisterRequest request);

    AuthenticationResponse sellerRegister(SellerRegisterRequest request);

    AuthenticationResponse pickupPointEmployeeRegister(PickupPointEmployeeRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
