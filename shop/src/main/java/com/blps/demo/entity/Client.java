package com.blps.demo.entity;

import com.blps.demo.user.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client")
@Accessors(chain = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "client")
    private Set<ProductOrder> productOrders = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Cart> carts = new HashSet<>();

    public void addProductOrder(ProductOrder productOrder){
        productOrders.add(productOrder);
        productOrder.setClient(this);
    }

    public void addCart(Cart cart){
        carts.add(cart);
        cart.setClient(this);
    }
}


