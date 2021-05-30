package nistagram.userservice.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.UserProfile;
import nistagram.userservice.repository.UserProfileRepository;
import nistagram.userservice.service.interfaces.IUserProfileService;

@Service
public class UserProfileService implements IUserProfileService {
	
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	public UserProfileService(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public ResponseEntity<String> checkIsUsernameTaken(String username) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().toLowerCase().equals(username.toLowerCase())) {
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_taken", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> checkIsEmailTaken(String email) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return new ResponseEntity<String>("taken", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_taken", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> registerUser(UserDTO userDTO) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().equals(userDTO.getUsername())) {
				return new ResponseEntity<String>("username", HttpStatus.ACCEPTED);
			}
			else if(userProfile.getEmail().equals(userDTO.getEmail())) {
				return new ResponseEntity<String>("email", HttpStatus.ACCEPTED);
			}
		}
		
		UserProfile newUserProfile = new UserProfile(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(),
				userDTO.getGender(), userDTO.getBirthdayDate(), userDTO.getWebsite(), userDTO.getBiography(), userDTO.getUsername(), 
				userDTO.getPassword(),false, new HashSet<UserProfile>(), new HashSet<UserProfile>(), new HashSet<UserProfile>(), new HashSet<UserProfile>());
		
		userProfileRepository.save(newUserProfile);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> login(LoginDTO loginDTO) {
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().equals(loginDTO.getUsername())) {
				if(userProfile.getPassword().equals(loginDTO.getPassword())) {
					UserDTO userDTO = new UserDTO(
						userProfile.getId(),
						userProfile.getName(),
						userProfile.getSurname(),
						userProfile.getEmail(),
						userProfile.getPhoneNumber(),
						userProfile.getGender(),
						userProfile.getBirthdayDate(),
						userProfile.getWebsite(),
						userProfile.getBiography(),
						userProfile.getUsername(),
						userProfile.getPassword()
					);
					return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
				}
				return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);
	}
}
