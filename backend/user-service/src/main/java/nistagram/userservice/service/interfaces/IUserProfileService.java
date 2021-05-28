package nistagram.userservice.service.interfaces;

import nistagram.userservice.dto.UserDTO;

public interface IUserProfileService {
	
	UserDTO registerUser(UserDTO userDTO);

}
