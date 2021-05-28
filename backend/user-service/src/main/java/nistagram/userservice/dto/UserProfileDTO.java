package nistagram.userservice.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import nistagram.userservice.model.UserProfile;
import nistagram.userservice.model.enums.GenderEnum;

public class UserProfileDTO extends UserDTO {
	
	private boolean isPrivate;
	
	private Set<UserProfile> followers = new HashSet<UserProfile>();
	 
	private Set<UserProfile> following = new HashSet<UserProfile>();
	 
	private Set<UserProfile> followRequests = new HashSet<UserProfile>();
	 
	private Set<UserProfile> closedFriends = new HashSet<UserProfile>();
	
	public UserProfileDTO(Long id, String name, String surname, String email, int phoneNumber, GenderEnum gender,
			Date birthdayDate, String website, String biography, String username, String password, boolean isPrivate,
			Set<UserProfile> followers, Set<UserProfile> following, Set<UserProfile> followRequests,
			Set<UserProfile> closedFriends) {
		super(id, name, surname, email, phoneNumber, gender, birthdayDate, website, biography, username, password);
		this.isPrivate = isPrivate;
		this.followers = followers;
		this.following = following;
		this.followRequests = followRequests;
		this.closedFriends = closedFriends;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Set<UserProfile> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserProfile> followers) {
		this.followers = followers;
	}

	public Set<UserProfile> getFollowing() {
		return following;
	}

	public void setFollowing(Set<UserProfile> following) {
		this.following = following;
	}

	public Set<UserProfile> getFollowRequests() {
		return followRequests;
	}

	public void setFollowRequests(Set<UserProfile> followRequests) {
		this.followRequests = followRequests;
	}

	public Set<UserProfile> getClosedFriends() {
		return closedFriends;
	}

	public void setClosedFriends(Set<UserProfile> closedFriends) {
		this.closedFriends = closedFriends;
	}
	
	
	
}
