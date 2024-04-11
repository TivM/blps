package com.blps.demo.services;

import com.blps.demo.entity.Client;

public interface ClientService {
    Client getClientById(int clientId);

    Client getClientByName(String name);
}
