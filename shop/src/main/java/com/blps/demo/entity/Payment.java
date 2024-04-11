package com.blps.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "payment")
@Accessors(chain = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "receive", nullable = false)
    private Integer receive;

    @Column(name = "change", nullable = false)
    private Integer change;

    @Column(name = "processor", nullable = false)
    private String processor;

    @OneToOne
    @JoinColumn(name = "product_order_id", nullable = false)
    private ProductOrder productOrder;

}
