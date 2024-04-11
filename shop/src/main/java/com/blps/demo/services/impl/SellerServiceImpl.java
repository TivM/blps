package com.blps.demo.services.impl;

import com.blps.demo.entity.Seller;
import com.blps.demo.repository.ProductRepository;
import com.blps.demo.repository.SellerRepository;
import com.blps.demo.services.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Override
    public Seller getById(int id) {
        var seller = sellerRepository.findById(id);
        return seller.isPresent() ? seller.get() : null;
    }

    @Override
    public Seller getByName(String name) {
        return null;
    }
}
