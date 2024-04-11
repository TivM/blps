package com.blps.demo.services;

import com.blps.demo.entity.Seller;

public interface SellerService {

    Seller getById(int id);

    Seller getByName(String name);
}
