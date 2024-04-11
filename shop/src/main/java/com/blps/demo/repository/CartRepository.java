package com.blps.demo.repository;

import com.blps.demo.entity.Cart;
import com.blps.demo.entity.compositekey.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {
    List<Cart> findByProductId(int productId);

    List<Cart> findByClientId(int productId);

    Optional<Cart> findByProductIdAndClientId(Integer productId, Integer clientId);
    @Transactional
    @Modifying
    void deleteByCartId(CartId cartId);
}
