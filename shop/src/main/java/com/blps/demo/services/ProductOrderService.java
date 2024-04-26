package com.blps.demo.services;

import com.blps.demo.entity.OrderedItem;
import com.blps.demo.entity.ProductOrder;
import com.blps.demo.entity.controllers.item.ItemWithStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ProductOrderService {
    ProductOrder getById(int orderId);

    ProductOrder getByClientId(int clientId);

    ProductOrder add(String pickupPointAddress, int clientId);

    void setCostOfDelivery(int orderId, Integer costOfDelivery);

    void setDeliveryTime(int orderId, Integer deliveryTime);

    List<ItemWithStatus> changeProductListStatus(List<ItemWithStatus> items, Set<Integer> existingIds, HashSet<OrderedItem> allItemsFromOrder);

    void sendStatusesToKafka(List<ItemWithStatus> items, HashSet<OrderedItem> allItemsFromOrder);
}
