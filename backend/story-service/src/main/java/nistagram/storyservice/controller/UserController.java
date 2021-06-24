package nistagram.storyservice.controller;

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

import nistagram.storyservice.dto.CreateHighLightDTO;
import nistagram.storyservice.model.HighLight;
import nistagram.storyservice.model.Story;
import nistagram.storyservice.service.UserService;

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
	
	@RequestMapping(value = "/get-highlights/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<HighLight>> getHighLights(@PathVariable("id") Long id) {
		return userService.getHighLights(id);
	}
	
	@RequestMapping(value = "/get-stories/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Story>> getStories(@PathVariable("id") Long id) {
		return userService.getAllStories(id);
	}
	
	@PostMapping(value = "/create-highlight", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<HighLight>> createCollection(@RequestBody CreateHighLightDTO createHighLightDTO) throws Exception {
		return userService.createHighLight(createHighLightDTO.getUserId(), createHighLightDTO.getName());
	}
	
}
