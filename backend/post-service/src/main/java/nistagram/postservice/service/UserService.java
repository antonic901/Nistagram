package nistagram.postservice.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.postservice.model.Post;
import nistagram.postservice.model.User;
import nistagram.postservice.repository.UserRepository;
import nistagram.postservice.service.interfaces.IUserService;

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
	public ResponseEntity<Set<Post>> getPostsForUser(Long id) {
		User user = userRepository.findById(id).get();
		return new ResponseEntity<Set<Post>>(user.getPosts(), HttpStatus.OK);
	}
	
	
}