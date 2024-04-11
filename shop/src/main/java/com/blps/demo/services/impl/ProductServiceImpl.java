package com.blps.demo.services.impl;

import com.blps.demo.entity.Product;
import com.blps.demo.entity.Seller;
import com.blps.demo.entity.controllers.item.FindItemsRequest;
import com.blps.demo.repository.ProductRepository;
import com.blps.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public int add(String name, int count, int price, String category, Integer size, Seller seller) {
        var entity = new Product();
        entity.setName(name);
        entity.setCount(count);
        entity.setPrice(price);
        entity.setSize(size);
        entity.setSeller(seller);
        entity.setCategory(category);
        return productRepository.save(entity).getId();
    }

    @Override
    public List<Product> filter(FindItemsRequest findItemsRequest) {
        var items = productRepository.filter(findItemsRequest.name(),
                findItemsRequest.category(),
                findItemsRequest.minPrice(),
                findItemsRequest.maxPrice(),
                findItemsRequest.minSize(),
                findItemsRequest.maxSize());
        return items;
    }

}
