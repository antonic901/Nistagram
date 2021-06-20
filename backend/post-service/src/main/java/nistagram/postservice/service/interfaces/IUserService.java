package nistagram.postservice.service.interfaces;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.model.Post;

public interface IUserService {
	ResponseEntity<String> createUser(Long id);
	ResponseEntity<Set<Post>> getPostsForUser(Long id);
}
