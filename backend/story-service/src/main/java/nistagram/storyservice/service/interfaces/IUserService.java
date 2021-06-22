package nistagram.storyservice.service.interfaces;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import nistagram.storyservice.model.HighLight;

public interface IUserService {
	ResponseEntity<String> createUser(Long id);
	ResponseEntity<Set<HighLight>> getHighLights(Long id);
}
