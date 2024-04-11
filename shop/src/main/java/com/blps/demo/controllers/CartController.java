package com.blps.demo.controllers;

import com.blps.demo.entity.Cart;
import com.blps.demo.entity.controllers.cart.AddCartRequest;
import com.blps.demo.entity.controllers.cart.AddCartResponse;
import com.blps.demo.entity.controllers.cart.GetCartResponse;
import com.blps.demo.entity.controllers.cart.ListGetCartResponse;
import com.blps.demo.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @PostMapping("/carts")
    public AddCartResponse addCart(@RequestBody AddCartRequest addCartRequest){
        Cart cart = cartService.add(addCartRequest.productId(), addCartRequest.clientId(), addCartRequest.count());
        return new AddCartResponse(cart.getProduct().getId(), cart.getCount());
    }

    @GetMapping("/carts/{clientId}")
    public ListGetCartResponse getCartByClientId(@PathVariable int clientId){
        var carts = cartService.getByClientId(clientId);
        var listCartResponses = new ArrayList<GetCartResponse>();
        for (var cart: carts) {
            listCartResponses.add(
                    new GetCartResponse(cart.getProduct().getId(), cart.getClient().getId(), cart.getCount())
            );
        }
        return new ListGetCartResponse(listCartResponses);
    }

    @DeleteMapping("/carts")
    public void getCartByClientId(
            @RequestParam(name = "productId") int productId,
            @RequestParam(name = "clientId") int clientId,
            @RequestParam(name = "count") int count
    ){
        cartService.deleteByProductIdAndClientId(productId, clientId, count);
    }
}

