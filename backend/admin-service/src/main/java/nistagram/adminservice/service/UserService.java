package nistagram.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.adminservice.model.User;
import nistagram.adminservice.repository.UserRepository;
import nistagram.adminservice.service.interfaces.IUserService;

@Service
public class UserService implements IUserService{

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<String> createUser(Long id) {
		User newUser = new User();
		newUser.setId(id);
		userRepository.save(newUser);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
