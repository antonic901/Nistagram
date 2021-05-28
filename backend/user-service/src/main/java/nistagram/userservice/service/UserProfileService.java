package nistagram.userservice.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public UserDTO registerUser(UserDTO userDTO) {
		System.out.print(userDTO.getName());
		for(UserProfile userProfile : userProfileRepository.findAll()) {
			if(userProfile.getUsername().equals(userDTO.getUsername())) {
				return null;
			}
		}
		
		UserProfile newUserProfile = new UserProfile(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(),
				userDTO.getGender(), userDTO.getBirthdayDate(), userDTO.getWebsite(), userDTO.getBiography(), userDTO.getUsername(), 
				userDTO.getPassword(),false, new HashSet<UserProfile>(), new HashSet<UserProfile>(), new HashSet<UserProfile>(), new HashSet<UserProfile>());
		
		userProfileRepository.save(newUserProfile);
		
		return userDTO;
	}
}
