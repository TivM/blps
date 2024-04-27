package com.notifier.demo.services;

import com.notifier.demo.dto.ItemForKafka;
import com.notifier.demo.entity.Notification;
import com.notifier.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStatusReceiver {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "blps-lab2-in", groupId = "user-group", containerFactory = "factory")
    public void listener(ItemForKafka item, Acknowledgment acknowledgment) {
        Notification notification = Notification.of(item);
        var notificationFromDB = notificationRepository.findById(notification.getNotificationId()).isPresent();
        if (!notificationFromDB){
            notificationRepository.save(notification);
        }
        acknowledgment.acknowledge();
    }
}
