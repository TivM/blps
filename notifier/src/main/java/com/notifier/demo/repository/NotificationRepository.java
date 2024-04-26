package com.notifier.demo.repository;

import com.notifier.demo.entity.Notification;
import com.notifier.demo.entity.NotificationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, NotificationId> {
    List<Notification> findByIsSent(Integer isSent);
}
