package nistagram.userservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.UserDTO;

public interface IUserProfileService {
	
	ResponseEntity<String> registerUser(UserDTO userDTO);
	ResponseEntity<String> checkIsUsernameTaken(String username);
	ResponseEntity<String> checkIsEmailTaken(String email);
	ResponseEntity<UserDTO> login(LoginDTO loginDTO);
	ResponseEntity<String> updateUser(UserDTO userDTO);
}
