package nistagram.postservice.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface ICollectionService {
	ResponseEntity<String> addToCollection(Long collectionId, Long postId, Long userId);
}
