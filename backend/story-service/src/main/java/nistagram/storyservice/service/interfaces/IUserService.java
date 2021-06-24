package nistagram.storyservice.service.interfaces;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import nistagram.storyservice.model.HighLight;
import nistagram.storyservice.model.Story;

public interface IUserService {
	ResponseEntity<String> createUser(Long id);
	ResponseEntity<Set<HighLight>> getHighLights(Long id);
	ResponseEntity<Set<HighLight>> createHighLight(Long userId, String name);
	ResponseEntity<Set<Story>> getAllStories(Long userId);
}
