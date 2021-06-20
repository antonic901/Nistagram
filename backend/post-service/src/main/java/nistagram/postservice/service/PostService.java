package nistagram.postservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.postservice.dto.NewPostDTO;
import nistagram.postservice.model.Caption;
import nistagram.postservice.model.Location;
import nistagram.postservice.model.Post;
import nistagram.postservice.model.Tag;
import nistagram.postservice.model.User;
import nistagram.postservice.repository.LocationRepository;
import nistagram.postservice.repository.PostRepository;
import nistagram.postservice.repository.UserRepository;
import nistagram.postservice.service.interfaces.IPostService;

@Service
public class PostService implements IPostService {
	
	private PostRepository postRepository;
	private LocationRepository locationRepository;
	private UserRepository userRepository;
	
	private TagService tagService;
	
	@Autowired
	public PostService(PostRepository postRepository, LocationRepository locationRepository, TagService tagService, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.locationRepository = locationRepository;
		this.tagService = tagService;
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<String> addNewPost(NewPostDTO newPostDTO) {
		Location location = null; 
		if(newPostDTO.getLocationId()!=null) location = locationRepository.findById(newPostDTO.getLocationId()).get();
		
		Caption caption = new Caption();
		caption.setDescription(newPostDTO.getDescription());
		for(String t : newPostDTO.getHashTags()) {
			Tag tag = tagService.getTagByName(t);
			if(tag != null) {
				caption.getTags().add(tag);
			}
		}
		
		User user = userRepository.findById(newPostDTO.getUserId()).get();
		
		Post newPost = new Post();
		newPost.setCaption(caption);
		newPost.setUser(user);
		newPost.setLocation(location);
		for(String s : newPostDTO.getImagesAndVideos()) {
			newPost.getImagesAndVideos().add(s);
		}
		postRepository.save(newPost);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
