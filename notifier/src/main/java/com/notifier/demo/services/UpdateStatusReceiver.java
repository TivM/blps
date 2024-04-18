package com.notifier.demo.services;

import com.notifier.demo.dto.ItemForKafka;
import com.notifier.demo.entity.Notification;
import com.notifier.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStatusReceiver {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "fuck-you", groupId = "user-group", containerFactory = "factory")
    public void listener(ItemForKafka item){
        notificationRepository.save(Notification.of(item));
    }
}
