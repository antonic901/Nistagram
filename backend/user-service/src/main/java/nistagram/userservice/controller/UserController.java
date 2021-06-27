package nistagram.userservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.User;
import nistagram.userservice.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(value = "/register-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) throws Exception
	{
		return userService.registerUser(userDTO);
	}
	
	@PostMapping(value = "/login-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) throws Exception{
		return userService.login(loginDTO);
	}
	
	@RequestMapping(value = "/get-by-username/{username}", method = RequestMethod.GET)
	public Long getByUsername(@PathVariable("username") String username) {
		return userService.getByUsername(username);
	}
	
	@RequestMapping(value = "/get-username-by-id/{id}", method = RequestMethod.GET)
	public String getByUsername(@PathVariable("id") Long id) {
		return userService.getUsernameById(id);
	}
	
}
