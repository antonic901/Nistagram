package nistagram.postservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nistagram.postservice.model.Notification;
import nistagram.postservice.repository.NotificationRepository;
import nistagram.postservice.repository.PostRepository;
import nistagram.postservice.repository.UserRepository;
import nistagram.postservice.service.interfaces.INotificationService;

@Service
public class NotificationService implements INotificationService {

	private NotificationRepository notificationRepository;
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	@Autowired
	public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository,
			PostRepository postRepository) {
		this.notificationRepository = notificationRepository;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public Notification notify(String description, Long senderId, Long receiverId, Long postId) {
		Notification notification = new Notification();
		notification.setDescription(description);
		notification.setViewed(false);
		notification.setPost(postRepository.findById(postId).get());
		notification.setSender(userRepository.findById(senderId).get());
		notification.setReceiver(userRepository.findById(receiverId).get());
		return notificationRepository.save(notification);
	}

	@Override
	public List<Notification> getAllNotifications(Long userId) {
		List<Notification> response = new ArrayList<Notification>();
		for(Notification n : notificationRepository.findAll()) {
			if(n.getReceiver().getId() == userId) {
				response.add(n);
			}
		}
		return response;
	}

	@Override
	public List<Notification> getNewNotifications(Long userId) {
		List<Notification> response = new ArrayList<Notification>();
		for(Notification n : notificationRepository.findAll()) {
			if(n.getReceiver().getId() == userId) {
				if(!n.isViewed()) {
					response.add(n);
				}
			}
		}
		return response;
	}

	@Override
	public String setNotificationStatus(Long id) {
		Notification notification = notificationRepository.findById(id).get();
		notification.setViewed(true);
		notificationRepository.save(notification);
		return "ok";
	}
	
	
	
}
