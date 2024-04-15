package com.notifier.demo.services;

import com.notifier.demo.dto.ItemWithStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UpdateStatusReceiver {

    @KafkaListener(topics = "order-status", groupId = "user-group", containerFactory = "factory")
    public void listener(ItemWithStatus item){
        System.out.println(item.status());
    }
}
