package com.blps.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "size")
    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "product")
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderedItem> orderedItems = new HashSet<>();

    public void addOrderedItem(OrderedItem orderedItem){
        orderedItems.add(orderedItem);
        orderedItem.setProduct(this);
    }

    public void addCart(Cart cart){
        carts.add(cart);
        cart.setProduct(this);
    }
}
