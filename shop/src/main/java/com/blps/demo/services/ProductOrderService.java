package com.blps.demo.services;

import com.blps.demo.entity.ProductOrder;

public interface ProductOrderService {
    ProductOrder getById(int orderId);

    ProductOrder getByClientId(int clientId);

    ProductOrder add(String pickupPointAddress, int clientId);

    void setCostOfDelivery(int orderId, Integer costOfDelivery);

    void setDeliveryTime(int orderId, Integer deliveryTime);
}
