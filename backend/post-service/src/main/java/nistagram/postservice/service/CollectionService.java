package nistagram.postservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.postservice.model.Collection;
import nistagram.postservice.model.Post;
import nistagram.postservice.repository.CollectionRepository;
import nistagram.postservice.repository.PostRepository;
import nistagram.postservice.service.interfaces.ICollectionService;

@Service
public class CollectionService implements ICollectionService {
	
	private CollectionRepository collectionRepository;
	private PostRepository postRepository;
	
	@Autowired
	public CollectionService(CollectionRepository collectionRepository, PostRepository postRepository) {
		this.collectionRepository = collectionRepository;
		this.postRepository = postRepository;
	}

	@Override
	public ResponseEntity<String> addToCollection(Long collectionId, Long postId) {
		Collection collection = collectionRepository.findById(collectionId).get();
		for(Post post : collection.getPosts()) {
			if(post.getId() == postId) {
				return new ResponseEntity<String>("exist", HttpStatus.OK);
			}
		}
		addToFavorites(postId);
		collection.getPosts().add(postRepository.findById(postId).get());
		collectionRepository.save(collection);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	private boolean addToFavorites(Long postId) {
		Collection collection = null;
		for(Collection c : collectionRepository.findAll()) {
			if(c.getName().toLowerCase().trim().equals("favorites")) {
				collection = c;
				break;
			}
		}
		
		for(Post post : collection.getPosts()) {
			if(post.getId() == postId) {
				return false;
			}
		}
		collection.getPosts().add(postRepository.findById(postId).get());
		collectionRepository.save(collection);
		return true;
	}
	
}
