package nistagram.postservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.dto.CreateCollectionDTO;
import nistagram.postservice.model.Collection;
import nistagram.postservice.model.Post;
import nistagram.postservice.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/create-user/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> createUser(@PathVariable("id") Long id) {
		return userService.createUser(id);
	}
	
	@RequestMapping(value = "/get-posts-for-user/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Post>> getPostsForUser(@PathVariable("id") Long id) {
		return userService.getPostsForUser(id);
	}
	
	@RequestMapping(value = "/get-collections/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Collection>> getCollections(@PathVariable("id") Long id) {
		return userService.getCollections(id);
	}
	
	@PostMapping(value = "/create-collection", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Collection>> createCollection(@RequestBody CreateCollectionDTO createCollectionDTO) throws Exception {
		return userService.createCollection(createCollectionDTO.getUserId(), createCollectionDTO.getName());
	}
	
}
