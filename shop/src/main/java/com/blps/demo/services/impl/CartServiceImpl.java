package com.blps.demo.services.impl;

import com.blps.demo.entity.Cart;
import com.blps.demo.entity.Client;
import com.blps.demo.entity.Product;
import com.blps.demo.entity.compositekey.CartId;
import com.blps.demo.exception.ResourceNotFoundException;
import com.blps.demo.repository.CartRepository;
import com.blps.demo.repository.ClientRepository;
import com.blps.demo.repository.ProductRepository;
import com.blps.demo.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    @Override
    @Transactional
    public Cart add(int productId, int clientId, int count) {
        Product productEntity = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("product not found")
        );

        Client clientEntity = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("client not found")
        );

        count = count > productEntity.getCount() ? productEntity.getCount() : count;

        CartId cartId = new CartId()
                .setClientId(clientEntity.getId())
                .setProductId(productEntity.getId());

        Cart cart = new Cart()
                .setCartId(cartId)
                .setProduct(productEntity)
                .setClient(clientEntity)
                .setCount(count);

        productEntity.addCart(cart);
        clientEntity.addCart(cart);

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getByClientId(int clientId) {
        return cartRepository.findByClientId(clientId);
    }

    @Override
    @Transactional
    public void deleteByProductIdAndClientId(int productId, int clientId, int count) {
        Cart cart = cartRepository.findByProductIdAndClientId(productId, clientId).orElseThrow(
                () -> new ResourceNotFoundException("client doesn't have product in the cart")
        );
        int countProducts = cart.getCount();

        CartId cartId = new CartId()
                .setProductId(cart.getProduct().getId())
                .setClientId(cart.getClient().getId());

        if (countProducts <= count){
            cartRepository.deleteByCartId(cartId);
        }
        else{
            int countToSave = countProducts - count;
            cart.setCount(countToSave);
            cartRepository.save(cart);
        }
    }
}
