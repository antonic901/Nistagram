package nistagram.userservice.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import nistagram.userservice.model.enums.UserType;

@Entity
@DiscriminatorValue("UserProfile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends User {
	
	@Column(nullable = true)
	private boolean isPrivate;
	
	//do user want to receive messsages from non-friends
	@Column(nullable = true)
	private boolean receiveMessage;
	
	@Column(nullable = true)
	private boolean isTaggable;
	
	@Column(nullable = true)
	private boolean tagNotificationEnabled;
	
	@Column(nullable = true)
	private boolean commentNotificationEnabled;
	
	@Column(nullable = true)
	private boolean likeDislikeNotificationEnabled;
	
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
	 @ManyToMany(cascade = CascadeType.ALL)
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
			String website, String biography, String username, String password, UserType userType, boolean isPrivate,
			boolean receiveMessage, boolean isTaggable, boolean tagNotificationEnabled,
			boolean commentNotificationEnabled, boolean likeDislikeNotificationEnabled, boolean isDeleted,
			Set<UserProfile> followers, Set<UserProfile> following, Set<FollowRequest> followRequests,
			Set<UserProfile> closedFriends, Set<UserProfile> mutedUsers, Set<UserProfile> blockedUsers) {
		super(name, surname, email, phoneNumber, gender, birthdayDate, website, biography, username, password,
				userType);
		this.isPrivate = isPrivate;
		this.receiveMessage = receiveMessage;
		this.isTaggable = isTaggable;
		this.tagNotificationEnabled = tagNotificationEnabled;
		this.commentNotificationEnabled = commentNotificationEnabled;
		this.likeDislikeNotificationEnabled = likeDislikeNotificationEnabled;
		this.isDeleted = isDeleted;
		this.followers = followers;
		this.following = following;
		this.followRequests = followRequests;
		this.closedFriends = closedFriends;
		this.mutedUsers = mutedUsers;
		this.blockedUsers = blockedUsers;
	}
	
}
