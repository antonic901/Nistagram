package nistagram.userservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.userservice.model.enums.GenderEnum;
import nistagram.userservice.model.enums.UserType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
	
	private String name;

	private String surname;

	private String email;

	private int phoneNumber;

	private GenderEnum gender;
	
	private UserType userType;

	private Date birthdayDate;

	private String website;

	private String biography;

	private String username;

	private String password;
	
	private boolean isPrivate;
	
	private boolean isTaggable;
	
	private boolean receiveMessage;

	private boolean tagNotificationEnabled;

	private boolean commentNotificationEnabled;

	private boolean likeDislikeNotificationEnabled;
	
}
