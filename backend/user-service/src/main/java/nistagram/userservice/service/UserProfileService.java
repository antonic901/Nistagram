package nistagram.userservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.SearchDTO;
import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.UserProfile;
import nistagram.userservice.model.FollowRequest;
import nistagram.userservice.repository.UserProfileRepository;
import nistagram.userservice.service.interfaces.IUserProfileService;

@Service
public class UserProfileService implements IUserProfileService {
	
	private UserProfileRepository userProfileRepository;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public UserProfileService(UserProfileRepository userProfileRepository, RestTemplate restTemplate) {
		this.userProfileRepository = userProfileRepository;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public UserProfile getById(Long id) {
		return userProfileRepository.findById(id).get();
	}
	
	@Override
	public ResponseEntity<String> checkIsUsernameTaken(String username) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().toLowerCase().equals(username.toLowerCase())) {
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_taken", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> checkIsEmailTaken(String email) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_taken", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> registerUser(UserDTO userDTO) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().equals(userDTO.getUsername())) {
				return new ResponseEntity<String>("username", HttpStatus.ACCEPTED);
			}
			else if(userProfile.getEmail().equals(userDTO.getEmail())) {
				return new ResponseEntity<String>("email", HttpStatus.ACCEPTED);
			}
		}
		
		UserProfile newUserProfile = new UserProfile(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(),
				userDTO.getGender(), userDTO.getBirthdayDate(), userDTO.getWebsite(), userDTO.getBiography(), userDTO.getUsername(), 
				userDTO.getPassword(), userDTO.getUserType(), false, true, true, true, true, true, true, new HashSet<UserProfile>(), new HashSet<UserProfile>(), 
				new HashSet<FollowRequest>(), new HashSet<UserProfile>(), new HashSet<UserProfile>(), new HashSet<UserProfile>());
		
		newUserProfile = userProfileRepository.save(newUserProfile);
		
		restTemplate.getForEntity("http://localhost:8082/api/user/create-user/" + newUserProfile.getId(), String.class);
		
		restTemplate.getForEntity("http://localhost:8083/api/user/create-user/" + newUserProfile.getId(), String.class);
		
		restTemplate.getForEntity("http://localhost:8084/api/user/create-user/" + newUserProfile.getId(), String.class);
		
		restTemplate.getForEntity("http://localhost:8082/api/tag/create-tag/@" + newUserProfile.getUsername(), String.class);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserProfile> login(LoginDTO loginDTO) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().equals(loginDTO.getUsername())) {
				if(userProfile.getPassword().equals(loginDTO.getPassword())) {
					return new ResponseEntity<UserProfile>(userProfile,HttpStatus.OK);
				}
				return new ResponseEntity<UserProfile>(new UserProfile(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<UserProfile>(new UserProfile(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateUser(UserDTO userDTO) {
		UserProfile userProfile = userProfileRepository.findById(userDTO.getId()).get();
		userProfile.setName(userDTO.getName());
		userProfile.setSurname(userDTO.getSurname());
		userProfile.setEmail(userDTO.getEmail());
		userProfile.setPhoneNumber(userDTO.getPhoneNumber());
		userProfile.setGender(userDTO.getGender());
		userProfile.setBirthdayDate(userDTO.getBirthdayDate());
		userProfile.setWebsite(userDTO.getWebsite());
		userProfile.setBiography(userDTO.getBiography());
		userProfile.setUsername(userDTO.getUsername());
		userProfile.setPassword(userDTO.getPassword());
		userProfile.setPrivate(userDTO.isPrivate());
		userProfile.setTaggable(userDTO.isTaggable());
		userProfile.setReceiveMessage(userDTO.isReceiveMessage());
		userProfile.setTagNotificationEnabled(userDTO.isTagNotificationEnabled());
		userProfile.setCommentNotificationEnabled(userDTO.isCommentNotificationEnabled());
		userProfile.setLikeDislikeNotificationEnabled(userDTO.isLikeDislikeNotificationEnabled());
		userProfileRepository.save(userProfile);
		restTemplate.getForEntity("http://localhost:8082/api/tag/create-tag/@" + userDTO.getUsername(), String.class);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserProfile>> searchByUsername(SearchDTO searchDTO) {
		List<UserProfile> result = new ArrayList<UserProfile>();
		for(UserProfile up : userProfileRepository.findAll()) {
			if(up.getUsername().toLowerCase().contains(searchDTO.getInput().toLowerCase())) {
				if(up.getId() != searchDTO.getUserId()) {
					if(!isBlocked(up, searchDTO.getUserId())) {
						result.add(up);	
					}
				}
			}
		}
		return new ResponseEntity<List<UserProfile>>(result, HttpStatus.OK);
	}
	
	private boolean isBlocked(UserProfile up, Long id) {
		for(UserProfile u : up.getBlockedUsers()) {
			if(u.getId() == id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean isFollowedBy(Long userPostId, Long userViewId) {
		UserProfile userProfile = userProfileRepository.findById(userPostId).get();
		for(UserProfile up : userProfile.getFollowers()) {
			if(up.getId() == userViewId) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean isMutedBy(Long userPostId, Long userViewId) {
		UserProfile userProfile = userProfileRepository.findById(userViewId).get();
		for(UserProfile up : userProfile.getMutedUsers()) {
			if(up.getId() == userPostId) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean isClosedFriend(Long userPostId, Long userViewId) {
		UserProfile userProfile = userProfileRepository.findById(userPostId).get();
		for(UserProfile up : userProfile.getClosedFriends()) {
			if(up.getId() == userViewId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean isPrivate(Long userPostId, Long userViewId) {
		UserProfile userProfile = userProfileRepository.findById(userPostId).get();
		return userProfile.isPrivate();
	}

	@Override
	public ResponseEntity<String> checkIsUserFollowing(Long userProfileId, Long userLoggedId) {
		UserProfile userLogged = userProfileRepository.findById(userLoggedId).get();
		for(UserProfile up : userLogged.getFollowing()) {
			if(up.getId() == userProfileId) {
				return new ResponseEntity<String>("following", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_following", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> checkIsUserBlocked(Long userProfileId, Long userLoggedId) {
		UserProfile userLogged = userProfileRepository.findById(userLoggedId).get();
		for(UserProfile up : userLogged.getBlockedUsers()) {
			if(up.getId() == userProfileId) {
				return new ResponseEntity<String>("blocked", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_blocked", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> checkIsUserMuted(Long userProfileId, Long userLoggedId) {
		UserProfile userLogged = userProfileRepository.findById(userLoggedId).get();
		for(UserProfile up : userLogged.getMutedUsers()) {
			if(up.getId() == userProfileId) {
				return new ResponseEntity<String>("muted", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_muted", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> checkIsUserClosedFriend(Long userProfileId, Long userLoggedId) {
		UserProfile userLogged = userProfileRepository.findById(userLoggedId).get();
		for(UserProfile up : userLogged.getClosedFriends()) {
			if(up.getId() == userProfileId) {
				return new ResponseEntity<String>("closedfriend", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_closedfriend", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> follow(Long userProfileId, Long userLoggedId) {
		
		boolean isPrivate = isPrivate(userProfileId, userLoggedId);
		
		if(isPrivate) {
			sendFollowRequest(userProfileId, userLoggedId);
			return new ResponseEntity<String>("request_sended", HttpStatus.OK);
		}
		
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getFollowing().add(userWhoReceive);
		userWhoReceive.getFollowers().add(userWhoRequest);
		userProfileRepository.save(userWhoRequest);
		userProfileRepository.save(userWhoReceive);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	private void sendFollowRequest(Long userProfileId, Long userLoggedId) {
		FollowRequest followRequest = new FollowRequest();
		followRequest.setActive(true);
		followRequest.setUserWhoRequested(userProfileRepository.findById(userLoggedId).get());
		followRequest.setUserWhoAccept(userProfileRepository.findById(userProfileId).get());
		
		UserProfile userProfile = userProfileRepository.findById(userProfileId).get();
		userProfile.getFollowRequests().add(followRequest);
		
		userProfileRepository.save(userProfile);
		
	}

	@Override
	public ResponseEntity<UserProfile> unfollow(Long userProfileId, Long userLoggedId) {
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getFollowing().remove(userWhoReceive);
		userWhoReceive.getFollowers().remove(userWhoRequest);
		userWhoRequest.getClosedFriends().remove(userWhoReceive);
		userWhoReceive.getClosedFriends().remove(userWhoRequest);
		userProfileRepository.save(userWhoRequest);
		userProfileRepository.save(userWhoReceive);
		return new ResponseEntity<UserProfile>(userWhoReceive, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserProfile> block(Long userProfileId, Long userLoggedId) {
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getBlockedUsers().add(userWhoReceive);
		userWhoRequest.getFollowing().remove(userWhoReceive);
		userWhoRequest.getFollowers().remove(userWhoReceive);
		userWhoReceive.getFollowers().remove(userWhoRequest);
		userWhoReceive.getFollowing().remove(userWhoRequest);
		userProfileRepository.save(userWhoRequest);
		userProfileRepository.save(userWhoReceive);
		return new ResponseEntity<UserProfile>(userWhoRequest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserProfile> unblock(Long userProfileId, Long userLoggedId) {
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getBlockedUsers().remove(userWhoReceive);
		userProfileRepository.save(userWhoRequest);
		return new ResponseEntity<UserProfile>(userWhoRequest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserProfile> mute(Long userProfileId, Long userLoggedId) {
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getMutedUsers().add(userWhoReceive);
		userProfileRepository.save(userWhoRequest);
		return new ResponseEntity<UserProfile>(userWhoRequest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserProfile> unmute(Long userProfileId, Long userLoggedId) {
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getMutedUsers().remove(userWhoReceive);
		userProfileRepository.save(userWhoRequest);
		return new ResponseEntity<UserProfile>(userWhoRequest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserProfile> addToClosedFriends(Long userProfileId, Long userLoggedId) {
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getClosedFriends().add(userWhoReceive);
		userProfileRepository.save(userWhoRequest);
		return new ResponseEntity<UserProfile>(userWhoRequest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserProfile> removeFromClosedFriends(Long userProfileId, Long userLoggedId) {
		UserProfile userWhoRequest = userProfileRepository.findById(userLoggedId).get();
		UserProfile userWhoReceive = userProfileRepository.findById(userProfileId).get();
		userWhoRequest.getClosedFriends().remove(userWhoReceive);
		userProfileRepository.save(userWhoRequest);
		return new ResponseEntity<UserProfile>(userWhoRequest, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<UserProfile>> getFollowers(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		Set<UserProfile> response = userProfile.getFollowers();
		return new ResponseEntity<Set<UserProfile>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<UserProfile>> getFollowing(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		Set<UserProfile> response = userProfile.getFollowing();
		return new ResponseEntity<Set<UserProfile>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<UserProfile>> getClosedFriends(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		Set<UserProfile> response = userProfile.getClosedFriends();
		return new ResponseEntity<Set<UserProfile>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<UserProfile>> getMutedUsers(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		Set<UserProfile> response = userProfile.getMutedUsers();
		return new ResponseEntity<Set<UserProfile>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<UserProfile>> getBlockedUsers(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		Set<UserProfile> response = userProfile.getBlockedUsers();
		return new ResponseEntity<Set<UserProfile>>(response, HttpStatus.OK);
	}

	@Override
	public String isTaggable(String username) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().trim().equals(username)) {
				if(userProfile.isTaggable()) {
					return "taggable";
				}
				else { 
					return "not_taggable";
				}
			}
		}
		return "doesnt_exist";
	}

	@Override
	public ResponseEntity<String> checkIsAwaitingForApprov(Long userProfileId, Long userLoggedId) {
		UserProfile userProfile = userProfileRepository.findById(userProfileId).get();
		
		for(FollowRequest fr : userProfile.getFollowRequests()) {
			if(fr.getUserWhoAccept().getId() == userProfileId && fr.getUserWhoRequested().getId() == userLoggedId) {
				if(fr.isActive()) {
					return new ResponseEntity<String>("awaiting", HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<String>("not_awaiting", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<FollowRequest>> getFollowRequestes(Long userId) {
		UserProfile userProfile = userProfileRepository.findById(userId).get();
		Set<FollowRequest> response = new HashSet<FollowRequest>();
		for(FollowRequest fr : userProfile.getFollowRequests()) {
			if(fr.isActive()) {
				response.add(fr);
			}
		}
		return new ResponseEntity<Set<FollowRequest>>(response, HttpStatus.OK);
	}

	@Override
	public Boolean tagNotificationEnaled(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		return userProfile.isTagNotificationEnabled();
	}

	@Override
	public Boolean commentNotificationEnaled(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		return userProfile.isCommentNotificationEnabled();
	}

	@Override
	public Boolean likeDislikeNotificationEnaled(Long id) {
		UserProfile userProfile = userProfileRepository.findById(id).get();
		return userProfile.isLikeDislikeNotificationEnabled();
	}
}
