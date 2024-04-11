package com.blps.demo.entity.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Getter
@Setter
@Embeddable
@Accessors(chain = true)
public class CartId implements Serializable {
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "client_id", nullable = false)
    private Integer clientId;
}
