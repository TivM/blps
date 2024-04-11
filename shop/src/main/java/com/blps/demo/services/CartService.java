package com.blps.demo.services;

import com.blps.demo.entity.Cart;

import java.util.List;

public interface CartService {
    Cart add(int productId, int clientId, int count);

    List<Cart> getByClientId(int clientId);

    void deleteByProductIdAndClientId(int productId, int clientId, int count);
}
