package com.blps.demo.repository;

import com.blps.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByName(String name);
    Optional<Client> findByEmail(String email);
}
