package com.blps.demo.services.impl;

import com.blps.demo.entity.Client;
import com.blps.demo.entity.ProductOrder;
import com.blps.demo.exception.ResourceNotFoundException;
import com.blps.demo.repository.ClientRepository;
import com.blps.demo.repository.ProductOrderRepository;
import com.blps.demo.services.ProductOrderService;
import com.blps.demo.services.utils.MapAddressToDeliveryCostAndTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ClientRepository clientRepository;
    private final ProductOrderRepository productOrderRepository;
    private final MapAddressToDeliveryCostAndTime mapAddressToDeliveryCostAndTime;


    @Override
    public ProductOrder getById(int orderId) {
        var order = productOrderRepository.findById(orderId);
        return order.orElse(null);
    }

    @Override
    public ProductOrder getByClientId(int clientId) {
        return null;
    }

    @Override
    @Transactional
    public ProductOrder add(String pickupPointAddress, int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("client not found")
        );

        List<Integer> deliveryCostAndTime = mapAddressToDeliveryCostAndTime
                .getMapAddressToDeliveryCostAndTime().get(pickupPointAddress);

        if (deliveryCostAndTime == null){
            throw new ResourceNotFoundException("address doesn't exists");
        }

        ProductOrder productOrder = new ProductOrder()
                .setPickupPointAddress(pickupPointAddress)
                .setCostOfDelivery(deliveryCostAndTime.get(0))
                .setDeliveryTime(deliveryCostAndTime.get(1));


        client.addProductOrder(productOrder);

        return productOrderRepository.save(productOrder);
    }

    @Override
    public void setCostOfDelivery(int orderId, Integer costOfDelivery) {

    }

    @Override
    public void setDeliveryTime(int orderId, Integer deliveryTime) {

    }
}
