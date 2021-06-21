package nistagram.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.userservice.dto.CheckFollowDTO;
import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.SearchDTO;
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
	
	@RequestMapping(value = "/check-username/{username}", method = RequestMethod.GET)
	public ResponseEntity<String> checkIsUsernameTaken(@PathVariable("username") String username) {
		return userProfileService.checkIsUsernameTaken(username);
	}
	
	@RequestMapping(value = "/check-email/{email}", method = RequestMethod.GET)
	public ResponseEntity<String> checkIsEmailTaken(@PathVariable("email") String email) {
		return userProfileService.checkIsEmailTaken(email);
	}
	
	@PostMapping(value = "/register-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody UserDTO userDTO) throws Exception
	{
		return userProfileService.registerUser(userDTO);
	}
	
	@PostMapping(value = "/login-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) throws Exception{
		return userProfileService.login(loginDTO);
	}
	
	@PostMapping(value = "/update-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) throws Exception
	{
		return userProfileService.updateUser(userDTO);
	}
	
	@PostMapping(value = "/search-by-username", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserProfile>> searchByUsername(@RequestBody SearchDTO searchDTO) throws Exception
	{
		return userProfileService.searchByUsername(searchDTO);
	}
	
	@PostMapping(value = "/is-followed-by", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean isFollowedBy(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.isFollowedBy(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/is-private", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean isPrivate(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.isPrivate(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	
	
}
