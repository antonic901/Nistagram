package nistagram.userservice.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.userservice.model.UserProfile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO extends UserDTO {
	
	private boolean isPrivate;
	
	private Set<UserProfile> followers = new HashSet<UserProfile>();
	 
	private Set<UserProfile> following = new HashSet<UserProfile>();
	 
	private Set<UserProfile> followRequests = new HashSet<UserProfile>();
	 
	private Set<UserProfile> closedFriends = new HashSet<UserProfile>();
	
}
