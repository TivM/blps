package com.blps.demo.services;

import com.blps.demo.entity.Product;
import com.blps.demo.entity.Seller;
import com.blps.demo.entity.controllers.item.FindItemsRequest;

import java.util.List;

public interface ProductService {
    int add(String name, int count, int price, String category, Integer size, Seller seller);

    List<Product> filter(FindItemsRequest findItemsRequest);
}
