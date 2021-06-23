package nistagram.storyservice.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IHighLightService {
	ResponseEntity<String> addToHighLight(Long highLightId, Long storyId);
}
