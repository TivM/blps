package com.notifier.demo.services;

import com.notifier.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.notifier.demo.entity.Status.INITIAL;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final NotificationRepository notificationRepository;

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinxgradxv@yandex.ru");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendNeededDeliveryNotificationToSeller() {}

    public void sendOrderInfoToClient() {}

    public void sendOrderCanceledToClient() {}

    public void sendItemDeliveredToClient() {}

    @Scheduled(cron = "* */1 * * * *")
    public void sendNotifications() {
        var notificationsList = notificationRepository.findByIsSent(0);
        for (var notification : notificationsList) {
                sendEmail(notification.getClientEmail(),
                        "Уведомление по заказу " + notification.getOrderId(),
                        "Товар " + notification.getProductId() + " перешел в статус " + notification.getStatus());
                sendEmail(notification.getSellerEmail(),
                        "Уведомление по заказу " + notification.getOrderId(),
                        "Товар " + notification.getProductId() + " перешел в статус " + notification.getStatus());
            notification.setIsSent(1);
            notificationRepository.save(notification);
        }
    }
}
