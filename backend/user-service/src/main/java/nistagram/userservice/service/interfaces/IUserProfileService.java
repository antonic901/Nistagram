package nistagram.userservice.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.SearchDTO;
import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.UserProfile;

public interface IUserProfileService {
	
	ResponseEntity<String> registerUser(UserDTO userDTO);
	ResponseEntity<String> checkIsUsernameTaken(String username);
	ResponseEntity<String> checkIsEmailTaken(String email);
	ResponseEntity<UserDTO> login(LoginDTO loginDTO);
	ResponseEntity<String> updateUser(UserDTO userDTO);
	ResponseEntity<List<UserProfile>> searchByUsername(SearchDTO searchDTO);
	Boolean isFollowedBy(Long userPostId, Long userViewId);
	Boolean isPrivate(Long userPostId, Long userViewId);
}
