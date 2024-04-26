package com.blps.demo.services.impl;

import com.blps.demo.entity.Client;
import com.blps.demo.entity.OrderedItem;
import com.blps.demo.entity.ProductOrder;
import com.blps.demo.entity.controllers.item.ItemForKafka;
import com.blps.demo.entity.controllers.item.ItemWithStatus;
import com.blps.demo.exception.ResourceNotFoundException;
import com.blps.demo.repository.ClientRepository;
import com.blps.demo.repository.ProductOrderRepository;
import com.blps.demo.services.OrderedItemService;
import com.blps.demo.services.ProductOrderService;
import com.blps.demo.services.utils.MapAddressToDeliveryCostAndTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ClientRepository clientRepository;
    private final ProductOrderRepository productOrderRepository;
    private final MapAddressToDeliveryCostAndTime mapAddressToDeliveryCostAndTime;
    private final OrderedItemService orderedItemService;
    private final KafkaTemplate<String, ItemForKafka> kafkaTemplate;

    @Value("${application.kafka.topic.name}")
    private String topicName;



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

    @Override
    @Transactional
    public List<ItemWithStatus> changeProductListStatus(List<ItemWithStatus> items, Set<Integer> existingIds, HashSet<OrderedItem> allItemsFromOrder) {
        var resultItems = new ArrayList<ItemWithStatus>();
        for (var item : items) {
            if (existingIds.contains(item.id())) {
                var orderedItem = allItemsFromOrder.stream().filter(itm -> itm.getProduct().getId().equals(item.id())).findFirst().get();
                orderedItem.setStatus(item.status());
                orderedItemService.update(orderedItem);
                var itemWithStatus = new ItemWithStatus(orderedItem.getProduct().getId(), orderedItem.getStatus());
                resultItems.add(itemWithStatus);
            }
        }
        return resultItems;
    }

    @Override
    public void sendStatusesToKafka(List<ItemWithStatus> items, HashSet<OrderedItem> allItemsFromOrder) {
        for (var item : items) {
            var orderedItem = allItemsFromOrder.stream().filter(itm -> itm.getProduct().getId().equals(item.id())).findFirst().get();
            kafkaTemplate.send(topicName, new ItemForKafka(orderedItem));
        }
    }
}
