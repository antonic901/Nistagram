package nistagram.userservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.User;

public interface IUserService {
	ResponseEntity<String> registerUser(UserDTO userDTO);
	ResponseEntity<User> login(LoginDTO loginDTO);
}
