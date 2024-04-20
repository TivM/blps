package com.blps.demo.services.impl;

import com.blps.demo.entity.Client;
import com.blps.demo.entity.PickupPointEmployee;
import com.blps.demo.entity.Seller;
import com.blps.demo.entity.User;
import com.blps.demo.entity.controllers.auth.*;
import com.blps.demo.exception.ResourceNotFoundException;
import com.blps.demo.repository.ClientRepository;
import com.blps.demo.repository.PickupPointEmployeeRepository;
import com.blps.demo.repository.SellerRepository;
import com.blps.demo.repository.UserRepository;
import com.blps.demo.services.AuthenticationService;
import com.blps.demo.services.JwtService;
import com.blps.demo.user.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;
    private final PickupPointEmployeeRepository pickupPointEmployeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthenticationResponse clientRegister(ClientRegisterRequest request) {
        var user = new User()
                .setEmail(request.email())
                .setPassword(passwordEncoder.encode(request.password()))
                .setRole(Role.CLIENT);

        var client = new Client()
                .setName(request.name())
                .setEmail(request.email())
                .setAge(request.age())
                .setAddress(request.address());

        userRepository.save(user);
        client = clientRepository.save(client);

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(client.getId(), jwtToken);
    }

    @Override
    @Transactional
    public AuthenticationResponse sellerRegister(SellerRegisterRequest request) {
        var user = new User()
                .setEmail(request.email())
                .setPassword(passwordEncoder.encode(request.password()))
                .setRole(Role.SELLER);

        var seller = new Seller()
                .setName(request.name())
                .setEmail(request.email())
                .setPassport(request.passport());

        userRepository.save(user);
        seller = sellerRepository.save(seller);

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(seller.getId(), jwtToken);
    }

    @Override
    @Transactional
    public AuthenticationResponse pickupPointEmployeeRegister(PickupPointEmployeeRequest request) {
        var user = new User()
                .setEmail(request.email())
                .setPassword(passwordEncoder.encode(request.password()))
                .setRole(Role.PICKUP_POINT_EMPLOYEE);

        var pickupPointEmployee = new PickupPointEmployee()
                .setName(request.name())
                .setEmail(request.email())
                .setPassport(request.passport());

        userRepository.save(user);
        pickupPointEmployee = pickupPointEmployeeRepository.save(pickupPointEmployee);

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(pickupPointEmployee.getId(), jwtToken);
    }

    @Override
    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        var user = userRepository.findByEmail(request.email())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        Integer id = getIdAfterAuthenticate(user);

        return new AuthenticationResponse(id, jwtToken);
    }

    private Integer getIdAfterAuthenticate(User user){
        switch (user.getRole()){
            case CLIENT -> {
                Client client = clientRepository.findByEmail(user.getEmail()).orElseThrow(
                        () -> new ResourceNotFoundException("Can't find client in client table")
                );
                return client.getId();
            }
            case SELLER -> {
                Seller seller = sellerRepository.findByEmail(user.getEmail()).orElseThrow(
                        () -> new ResourceNotFoundException("Can't find seller in seller table")
                );
                return seller.getId();
            }
            case PICKUP_POINT_EMPLOYEE -> {
                PickupPointEmployee employee = pickupPointEmployeeRepository.findByEmail(user.getEmail()).orElseThrow(
                        () -> new ResourceNotFoundException("Can't find employee in pickup_point_employee table")
                );
                return employee.getId();
            }
            default -> throw new ResourceNotFoundException("Role doesn't exist");
        }
    }
}
