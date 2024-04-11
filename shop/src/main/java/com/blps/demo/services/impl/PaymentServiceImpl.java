package com.blps.demo.services.impl;

import com.blps.demo.entity.OrderedItem;
import com.blps.demo.entity.Payment;
import com.blps.demo.entity.ProductOrder;
import com.blps.demo.exception.ResourceNotFoundException;
import com.blps.demo.repository.PaymentRepository;
import com.blps.demo.repository.ProductOrderRepository;
import com.blps.demo.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ProductOrderRepository productOrderRepository;

    @Override
    public Payment addPayment(int receive, String processor, int productOrderId) {
        ProductOrder productOrder = productOrderRepository.findById(productOrderId).orElseThrow(
                () -> new ResourceNotFoundException("product order doesn't exist")
        );
        int cost = 0;
        Set<OrderedItem> orderedItemSet = productOrder.getOrderedItems();
        for (var item : orderedItemSet){
            cost += item.getCount() * item.getProduct().getPrice();
        }
        cost += productOrder.getCostOfDelivery();
        int change = receive - cost;
        if (change < 0){
            throw new ResourceNotFoundException("client doesn't have enough money");
        }
        Payment payment = new Payment()
                .setReceive(receive)
                .setChange(change)
                .setProcessor(processor)
                .setProductOrder(productOrder);

        productOrder.setPayment(payment);

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(int productOrderId) {
        return paymentRepository.findByProductOrderId(productOrderId);
    }
}
