package nistagram.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nistagram.userservice.dto.LoginDTO;
import nistagram.userservice.dto.UserDTO;
import nistagram.userservice.model.User;
import nistagram.userservice.model.UserProfile;
import nistagram.userservice.repository.UserRepository;
import nistagram.userservice.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
	
	private UserRepository userRepository;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public UserService(UserRepository userRepository, RestTemplate restTemplate) {
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
	}
	
	@Override
	public ResponseEntity<String> registerUser(UserDTO userDTO) {
		for(User user : userRepository.findAll()) {
			if(user.getUsername().equals(userDTO.getUsername())) {
				return new ResponseEntity<String>("username", HttpStatus.ACCEPTED);
			}
			else if(user.getEmail().equals(userDTO.getEmail())) {
				return new ResponseEntity<String>("email", HttpStatus.ACCEPTED);
			}
		}
		
		User newUser = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(),
				userDTO.getGender(), userDTO.getBirthdayDate(), userDTO.getWebsite(), userDTO.getBiography(), userDTO.getUsername(), 
				userDTO.getPassword(), userDTO.getUserType());
		
		newUser = userRepository.save(newUser);
		
		restTemplate.getForEntity("http://localhost:8084/api/admin/create-admin/" + newUser.getId(), String.class);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<User> login(LoginDTO loginDTO) {
		for(User user : userRepository.findAll()) {
			if(user.getUsername().equals(loginDTO.getUsername())) {
				if(user.getPassword().equals(loginDTO.getPassword())) {
					return new ResponseEntity<User>(user,HttpStatus.OK);
				}
				return new ResponseEntity<User>(new UserProfile(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<User>(new UserProfile(), HttpStatus.OK);
	}
	
}
