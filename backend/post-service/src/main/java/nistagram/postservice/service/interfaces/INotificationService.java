package nistagram.postservice.service.interfaces;

import java.util.List;

import nistagram.postservice.model.Notification;

public interface INotificationService {
	Notification notify(String description, Long senderId, Long receiverId, Long postId);
	List<Notification> getAllNotifications(Long userId);
	List<Notification> getNewNotifications(Long userId);
	String setNotificationStatus(Long id);
}
