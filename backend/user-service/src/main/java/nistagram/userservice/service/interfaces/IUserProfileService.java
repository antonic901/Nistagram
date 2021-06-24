package nistagram.userservice.service.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.SearchDTO;
import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.UserProfile;

public interface IUserProfileService {
	UserProfile getById(Long id);
	ResponseEntity<String> registerUser(UserDTO userDTO);
	ResponseEntity<String> checkIsUsernameTaken(String username);
	ResponseEntity<String> checkIsEmailTaken(String email);
	ResponseEntity<UserProfile> login(LoginDTO loginDTO);
	ResponseEntity<String> updateUser(UserDTO userDTO);
	ResponseEntity<List<UserProfile>> searchByUsername(SearchDTO searchDTO);
	Boolean isFollowedBy(Long userPostId, Long userViewId);
	Boolean isMutedBy(Long userPostId, Long userViewId);
	Boolean isClosedFriend(Long userPostId, Long userViewId);
	Boolean isPrivate(Long userPostId, Long userViewId);
	String isTaggable(String username);
	ResponseEntity<String> checkIsUserFollowing(Long userProfileId, Long userLoggedId);
	ResponseEntity<String> checkIsUserBlocked(Long userProfileId, Long userLoggedId);
	ResponseEntity<String> checkIsUserMuted(Long userProfileId, Long userLoggedId);
	ResponseEntity<String> checkIsUserClosedFriend(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile> follow(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile> unfollow(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile> block(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile> unblock(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile> mute(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile> unmute(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile>	addToClosedFriends(Long userProfileId, Long userLoggedId);
	ResponseEntity<UserProfile> removeFromClosedFriends(Long userProfileId, Long userLoggedId);
	ResponseEntity<Set<UserProfile>> getFollowers(Long id);
	ResponseEntity<Set<UserProfile>> getFollowing(Long id);
	ResponseEntity<Set<UserProfile>> getClosedFriends(Long id);
	ResponseEntity<Set<UserProfile>> getMutedUsers(Long id);
	ResponseEntity<Set<UserProfile>> getBlockedUsers(Long id);
}
