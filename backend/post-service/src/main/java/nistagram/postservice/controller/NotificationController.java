package nistagram.postservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.dto.AddNotificationDTO;
import nistagram.postservice.model.Notification;
import nistagram.postservice.service.NotificationService;

@RestController
@RequestMapping(value = "api/notification")
public class NotificationController {
	
	private NotificationService notificationService;

	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@PostMapping(value = "/notify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notification> searchByHashTag(@RequestBody AddNotificationDTO addNotificationDTO) throws Exception
	{
		return new ResponseEntity<Notification>(notificationService.notify(addNotificationDTO.getDescription(), addNotificationDTO.getSender(), addNotificationDTO.getReceiver(), addNotificationDTO.getPost()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-notifications/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> getNotifications(@PathVariable("id") Long id) {
		return new ResponseEntity<List<Notification>>(notificationService.getAllNotifications(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-new-notifications/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Notification>> checkNotifications(@PathVariable("id") Long id) {
		return new ResponseEntity<List<Notification>>(notificationService.getNewNotifications(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/set-notification-status/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> setNotificationStatus(@PathVariable("id") Long id) {
		return new ResponseEntity<String>(notificationService.setNotificationStatus(id),HttpStatus.OK);
	}
	
}
