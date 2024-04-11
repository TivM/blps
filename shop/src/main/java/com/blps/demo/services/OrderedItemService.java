package com.blps.demo.services;

import com.blps.demo.entity.OrderedItem;
import com.blps.demo.entity.Product;
import com.blps.demo.entity.ProductOrder;

import java.util.List;

public interface OrderedItemService {
    OrderedItem add(ProductOrder productOrder, Product product, String status, int count);

    List<OrderedItem> getByOrderId(int order_id);

    OrderedItem setStatus(int id, String status);

    OrderedItem update(OrderedItem orderedItem);
}
