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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
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
public class UserProfile extends User {
	
	@Column(nullable = false)
	private boolean isPrivate;
	
	//do user want to receive messsages from non-friends
	@Column(nullable = false)
	private boolean receiveMessage;
	
	@Column(nullable = false)
	private boolean isTaggable;
	
	@Column
	private boolean isDeleted;
	 
	@JsonIgnore
	 @ManyToMany
	 @JoinTable (
	    		name = "followers",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "follower_user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> followers = new HashSet<UserProfile>();
	 
	@JsonIgnore
	 @ManyToMany
	 @JoinTable (
	    		name = "following",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "following_user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> following = new HashSet<UserProfile>();
	 
	@JsonIgnore
	 @ManyToMany
	 @JoinTable (
	    		name = "follow_requests",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "follower_request_user_id", referencedColumnName = "id")
	    )
	 private Set<FollowRequest> followRequests = new HashSet<FollowRequest>();
	 
	@JsonIgnore
	 @ManyToMany
	 @JoinTable (
	    		name = "closed_friends",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "closefriend__user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> closedFriends = new HashSet<UserProfile>();
	 
	 @JsonIgnore
	 @ManyToMany
	 @JoinTable (
	    		name = "muted_friends",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "mutedfriend__user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> mutedUsers = new HashSet<UserProfile>();
	 
	 @JsonIgnore
	 @ManyToMany
	 @JoinTable (
	    		name = "blocked_friends",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "blockedfriend__user_id", referencedColumnName = "id")
	    )
	 private Set<UserProfile> blockedUsers = new HashSet<UserProfile>();
	 
	 public UserProfile(String name, String surname, String email, int phoneNumber, GenderEnum gender, Date birthdayDate,
			String website, String biography, String username, String password, boolean isPrivate,
			Set<UserProfile> followers, Set<UserProfile> following, Set<FollowRequest> followRequests,
			Set<UserProfile> closedFriends) {
		super(name, surname, email, phoneNumber, gender, birthdayDate, website, biography, username, password);
		this.isPrivate = isPrivate;
		this.followers = followers;
		this.following = following;
		this.followRequests = followRequests;
		this.closedFriends = closedFriends;
	}
	 
}
