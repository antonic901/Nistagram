package nistagram.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.UserProfile;
import nistagram.userservice.service.UserProfileService;

@RestController
@RequestMapping(value = "api/userprofile")
public class UserProfileController {
	
	private UserProfileService userProfileService;
	
	@Autowired
	public UserProfileController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	
	@RequestMapping("/get-user/{userProfileId}")
	public UserProfile getUserProfileById(@PathVariable("userProfileId") String userProfileId) {
		return null;
	}
	
	@PostMapping(value = "/register-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) throws Exception
	{
		UserDTO registeredUser = userProfileService.registerUser(userDTO);
		
		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.OK);
	}
}
