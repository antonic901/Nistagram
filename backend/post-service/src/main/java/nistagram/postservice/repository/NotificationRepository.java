package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
