package nistagram.storyservice.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.storyservice.model.HighLight;
import nistagram.storyservice.model.User;
import nistagram.storyservice.repository.UserRepository;
import nistagram.storyservice.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
	
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

	@Override
	public ResponseEntity<Set<HighLight>> getHighLights(Long id) {
		User user = userRepository.findById(id).get();
		Set<HighLight> response = new HashSet<HighLight>();
		for(HighLight hl : user.getHighLights()) {
			if(hl.getStories().size() != 0) {
				response.add(hl);
			}
		}
		return new ResponseEntity<Set<HighLight>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<HighLight>> createHighLight(Long userId, String name) {
		HighLight newHighLight = new HighLight();
		newHighLight.setName(name);
		User user = userRepository.findById(userId).get();
		user.getHighLights().add(newHighLight);
		userRepository.save(user);
		return new ResponseEntity<Set<HighLight>>(user.getHighLights(), HttpStatus.OK);
	}
	
}
