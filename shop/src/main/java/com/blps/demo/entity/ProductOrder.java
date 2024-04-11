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
@Table(name = "product_order")
@Accessors(chain = true)
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pickup_point_address", nullable = false)
    private String pickupPointAddress;

    @Column(name = "delivery_time", nullable = false)
    private Integer deliveryTime;

    @Column(name = "cost_of_delivery", nullable = false)
    private Integer costOfDelivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "productOrder")
    private Set<OrderedItem> orderedItems = new HashSet<>();

    @OneToOne(mappedBy = "productOrder")
    private Payment payment;

    public void addOrderedItem(OrderedItem orderedItem){
        orderedItems.add(orderedItem);
        orderedItem.setProductOrder(this);
    }
}
