package com.blps.demo.services.impl;

import com.blps.demo.entity.OrderedItem;
import com.blps.demo.entity.Product;
import com.blps.demo.entity.ProductOrder;
import com.blps.demo.repository.OrderedItemRepository;
import com.blps.demo.repository.ProductOrderRepository;
import com.blps.demo.repository.ProductRepository;
import com.blps.demo.services.OrderedItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderedItemServiceImpl implements OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;
    @Override
    public OrderedItem add(ProductOrder productOrder, Product product, String status, int count) {

        OrderedItem orderedItem = new OrderedItem()
                .setStatus(status)
                .setProductOrder(productOrder)
                .setProduct(product)
                .setCount(count);

        productOrder.addOrderedItem(orderedItem);
        product.addOrderedItem(orderedItem);

        return orderedItemRepository.save(orderedItem);
    }

    @Override
    public List<OrderedItem> getByOrderId(int orderId) {
        return orderedItemRepository.findByProductOrderId(orderId);
    }

    @Override
    public OrderedItem setStatus(int id, String status) {
        orderedItemRepository.setStatusById(id, status);
        var orderedItem = orderedItemRepository.findById(id).get();
        return orderedItem;
    }

    @Override
    public OrderedItem update(OrderedItem orderedItem) {
        return orderedItemRepository.save(orderedItem);
    }
}
