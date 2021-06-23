package nistagram.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.adminservice.dto.AddNewPostDTO;
import nistagram.adminservice.model.Caption;
import nistagram.adminservice.model.Post;
import nistagram.adminservice.repository.PostRepository;
import nistagram.adminservice.service.interfaces.IPostService;

@Service
public class PostService implements IPostService {
	
	private PostRepository postRepository;
	
	@Autowired
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public ResponseEntity<String> createPost(AddNewPostDTO newPostDTO) {
		Post post = new Post();
		post.setId(newPostDTO.getPostId());
		post.setDeleted(false);
		
		Caption caption = new Caption();
		caption.setDescription(newPostDTO.getDescription());
		caption.setId(newPostDTO.getCaptionId());
		post.setCaption(caption);
		
		post.setImagesAndVideos(newPostDTO.getImagesAndVideos());
		
		postRepository.save(post);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
