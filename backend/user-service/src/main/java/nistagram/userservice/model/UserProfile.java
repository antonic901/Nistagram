package nistagram.userservice.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.userservice.model.enums.GenderEnum;

@Entity
@DiscriminatorValue("UserProfile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserProfile extends User {
	
	@Column(nullable = false)
	private boolean isPrivate;
	 
	 @ManyToMany
	 @JoinTable (
	    		name = "followers",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "follower_user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> followers = new HashSet<UserProfile>();
	 
	 @ManyToMany
	 @JoinTable (
	    		name = "following",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "following_user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> following = new HashSet<UserProfile>();
	 
	 @ManyToMany
	 @JoinTable (
	    		name = "follow_requests",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "follower_request_user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> followRequests = new HashSet<UserProfile>();
	 
	 @ManyToMany
	 @JoinTable (
	    		name = "closed_friends",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "closefriend__user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> closedFriends = new HashSet<UserProfile>();
	 
	public UserProfile(String name, String surname, String email, int phoneNumber, GenderEnum gender, Date birthdayDate,
			String website, String biography, String username, String password, boolean isPrivate,
			Set<UserProfile> followers, Set<UserProfile> following, Set<UserProfile> followRequests,
			Set<UserProfile> closedFriends) {
		super(name, surname, email, phoneNumber, gender, birthdayDate, website, biography, username, password);
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
