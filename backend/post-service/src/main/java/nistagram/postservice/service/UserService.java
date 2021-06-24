package nistagram.postservice.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.postservice.model.Collection;
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
		Collection collection = new Collection();
		collection.setName("Favorites");
		newUser.getCollections().add(collection);
		userRepository.save(newUser);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<Post>> getPostsForUser(Long id) {
		User user = userRepository.findById(id).get();
		return new ResponseEntity<Set<Post>>(user.getPosts(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<Collection>> getCollections(Long id) {
		User user = userRepository.findById(id).get();
		Set<Collection> response = user.getCollections();
		return new ResponseEntity<Set<Collection>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<Collection>> createCollection(Long userId, String name) {
		User user = userRepository.findById(userId).get();
		
		if(isAlreadyExist(user,name)) {
			return getCollections(user.getId());
		}
		
		Collection newCollection = new Collection();
		newCollection.setName(name);
		user.getCollections().add(newCollection);
		userRepository.save(user);
		return new ResponseEntity<Set<Collection>>(user.getCollections(), HttpStatus.OK);
	}
	
	private boolean isAlreadyExist(User user, String name) {
		for(Collection c : user.getCollections()) {
			if(c.getName().toLowerCase().trim().equals(name.toLowerCase().trim())) {
				return true;
			}
		}
		return false;
	}
	
	
}
