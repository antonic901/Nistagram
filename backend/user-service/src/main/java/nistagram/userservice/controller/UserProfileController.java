package nistagram.userservice.controller;

import java.util.List;
import java.util.Set;

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
	
	@RequestMapping(value = "/get-by-id/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<UserProfile>(userProfileService.getById(id),HttpStatus.OK);
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
	
	@PostMapping(value = "/is-muted-by", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean isMutedBy(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.isMutedBy(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/is-closed-friend", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean isClosedFriend(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.isClosedFriend(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/is-private", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean isPrivate(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.isPrivate(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/check-is-user-following", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkIsUserFollowing(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.checkIsUserFollowing(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/check-is-user-blocked", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkIsUserBlocked(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.checkIsUserBlocked(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/check-is-user-muted", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkIsUserMuted(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.checkIsUserMuted(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/check-is-user-closed-friend", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkIsUserClosedFriend(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.checkIsUserClosedFriend(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/follow", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> follow(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.follow(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/unfollow", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> unfollow(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.unfollow(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/block", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> block(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.block(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/unblock", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> unblock(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.unblock(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/mute", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> mute(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.mute(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/unmute", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> unmute(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.unmute(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/add-to-closed-friends", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> addToClosedFriends(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.addToClosedFriends(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@PostMapping(value = "/remove-from-closed-friends", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfile> removeFromoClosedFriends(@RequestBody CheckFollowDTO checkFollowDTO) throws Exception {
		return userProfileService.removeFromClosedFriends(checkFollowDTO.getUserPostId(), checkFollowDTO.getUserViewId());
	}
	
	@RequestMapping(value = "/get-followers/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<UserProfile>> getFollowers(@PathVariable("id") Long id) {
		return userProfileService.getFollowers(id);
	}
	
	@RequestMapping(value = "/get-following/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<UserProfile>> getFollowing(@PathVariable("id") Long id) {
		return userProfileService.getFollowing(id);
	}
	
	@RequestMapping(value = "/get-closed-friends/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<UserProfile>> getClosedFriends(@PathVariable("id") Long id) {
		return userProfileService.getClosedFriends(id);
	}
	
	@RequestMapping(value = "/get-muted-users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<UserProfile>> getMutedUsers(@PathVariable("id") Long id) {
		return userProfileService.getMutedUsers(id);
	}
	
	@RequestMapping(value = "/get-blocked-users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<UserProfile>> getBlockedUsers(@PathVariable("id") Long id) {
		return userProfileService.getBlockedUsers(id);
	}
}
