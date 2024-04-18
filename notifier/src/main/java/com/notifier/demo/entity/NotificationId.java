package com.notifier.demo.entity;

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
public class NotificationId implements Serializable {
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "status", nullable = false)
    private String status;
}
