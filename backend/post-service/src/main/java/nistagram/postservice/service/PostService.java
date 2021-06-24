package nistagram.postservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nistagram.postservice.dto.AddNewPostDTO;
import nistagram.postservice.dto.CheckFollowDTO;
import nistagram.postservice.dto.NewPostDTO;
import nistagram.postservice.dto.SearchDTO;
import nistagram.postservice.model.Caption;
import nistagram.postservice.model.LikeDislike;
import nistagram.postservice.model.Location;
import nistagram.postservice.model.Post;
import nistagram.postservice.model.Tag;
import nistagram.postservice.model.User;
import nistagram.postservice.repository.LikeDislikeRepository;
import nistagram.postservice.repository.LocationRepository;
import nistagram.postservice.repository.PostRepository;
import nistagram.postservice.repository.UserRepository;
import nistagram.postservice.service.interfaces.IPostService;

@Service
public class PostService implements IPostService {
	
	private PostRepository postRepository;
	private LocationRepository locationRepository;
	private UserRepository userRepository;
	private LikeDislikeRepository likeDislikeRepository;
	
	private TagService tagService;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public PostService(PostRepository postRepository, LocationRepository locationRepository, TagService tagService, 
			UserRepository userRepository, LikeDislikeRepository likeDislikeRepository , RestTemplate restTemplate) {
		this.postRepository = postRepository;
		this.locationRepository = locationRepository;
		this.tagService = tagService;
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
		this.likeDislikeRepository = likeDislikeRepository;
	}

	@Override
	public ResponseEntity<String> addNewPost(NewPostDTO newPostDTO) {
		Location location = null; 
		if(newPostDTO.getLocationId()!=null) location = locationRepository.findById(newPostDTO.getLocationId()).get();
		
		for(String profileTag : newPostDTO.getProfileTags()) {
			String check = profileTag.replace("@", "");
			String response = restTemplate.getForObject("http://localhost:8081/api/userprofile/is-taggable/" + check, String.class);
			if(response.equals("not_taggable")) {
				return new ResponseEntity<String>("User " + profileTag + " can't be tagged!", HttpStatus.OK);
			}
			else if(response.equals("doesnt_exist")) {
				return new ResponseEntity<String>("User " + profileTag + " doesn't exist!", HttpStatus.OK);
			}
		}
		
		Caption caption = new Caption();
		caption.setDescription(newPostDTO.getDescription());
		for(String t : newPostDTO.getHashTags()) {
			Tag tag = tagService.getTagByName(t);
			if(tag != null) {
				caption.getTags().add(tag);
			}
		}
		
		for(String t : newPostDTO.getProfileTags()) {
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
		newPost = postRepository.save(newPost);
		
		AddNewPostDTO addNewPostDTO = new AddNewPostDTO();
		addNewPostDTO.setPostId(newPost.getId());
		addNewPostDTO.setImagesAndVideos(newPost.getImagesAndVideos());
		addNewPostDTO.setCaptionId(newPost.getCaption().getId());
		addNewPostDTO.setDescription(newPost.getCaption().getDescription());
		
		restTemplate.postForEntity("http://localhost:8084/api/post/create-post", addNewPostDTO, String.class);
		
		return new ResponseEntity<String>("Post is succesfully added!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Post>> searchByHashTag(SearchDTO searchDTO) {
		List<Post> result = new ArrayList<Post>();
		for(Post p : postRepository.findAll()) {
			if(filterByTag(p, searchDTO.getInput())) {
				if(filterByPrivate(p,searchDTO.getUserId())) {
					result.add(p);
				}
			}
		}
		return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
	}
	
	private boolean filterByTag(Post p, String tag) {
		for(Tag t : p.getCaption().getTags()) {
			if(t.getName().toLowerCase().contains(tag.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean filterByPrivate(Post p, Long userId) {
		CheckFollowDTO checkFollowDTO = new CheckFollowDTO(p.getUser().getId(),userId);
		if(userId == null) {
			Boolean isPrivate = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-private", checkFollowDTO, Boolean.class);
			if(isPrivate) {
				return false;
			}
			else return true;
		}
		else {
			Boolean isPrivate = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-private", checkFollowDTO, Boolean.class);
			if(isPrivate) {
				Boolean isFollowed = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-followed-by", checkFollowDTO, Boolean.class);
				if(isFollowed) {
					return true;
				}
				else return false;
			}
			else return true;
		}
	}

	@Override
	public ResponseEntity<List<Post>> searchByLocation(SearchDTO searchDTO) {
		List<Post> result = new ArrayList<Post>();
		for(Post p : postRepository.findAll()) {
			if(filterByLocation(p, searchDTO.getInput().toLowerCase())) {
				if(filterByPrivate(p,searchDTO.getUserId())) {
					result.add(p);
				}
			}
		}
		return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
	}
	
	private boolean filterByLocation(Post p, String location) {
		String l;
		try {
			l = p.getLocation().getCountry().toLowerCase() + ", " + p.getLocation().getCity().toLowerCase() + ", " + p.getLocation().getStreet().toLowerCase();
		} catch(Exception e) {
			return false;
		}
		if(l.contains(location)) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<Set<Post>> getPostsForFeed(Long id) {
		Set<Post> response = new HashSet<Post>();
		if(id == -1) {
			for(Post post : postRepository.findAll()) {
				CheckFollowDTO checkFollowDTO = new CheckFollowDTO(post.getUser().getId(),id);
				Boolean isPrivate = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-private", checkFollowDTO, Boolean.class);
				if(!isPrivate) {
					response.add(post);
				}
			}
		}
		else {
			for(Post post : postRepository.findAll()) {
				if(post.getUser().getId() != id) {
					CheckFollowDTO checkFollowDTO = new CheckFollowDTO(post.getUser().getId(),id);
					Boolean isUserFollowing = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-followed-by", checkFollowDTO, Boolean.class);
					Boolean isMuted = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-muted-by", checkFollowDTO, Boolean.class);
					if(isUserFollowing) {
						if(!isMuted) {
							response.add(post);
						}
					}
				}
			}
		}
		return new ResponseEntity<Set<Post>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Set<LikeDislike>> addLikeOrDislike(Long postId, Long userId, Boolean isLike) {
		Post post = postRepository.findById(postId).get();
		for(LikeDislike ld : post.getLikesDislikes()) {
			if(ld.getUser().getId() == userId) {
				if(ld.isLike()) {
					if(isLike) {
						return new ResponseEntity<Set<LikeDislike>>(post.getLikesDislikes(), HttpStatus.OK);
					}
					else {
						ld.setLike(isLike);
						likeDislikeRepository.save(ld);
						return new ResponseEntity<Set<LikeDislike>>(post.getLikesDislikes(), HttpStatus.OK);
					}
				}
				else {
					if(!isLike) {
						return new ResponseEntity<Set<LikeDislike>>(post.getLikesDislikes(), HttpStatus.OK);
					}
					else {
						ld.setLike(isLike);
						likeDislikeRepository.save(ld);
						return new ResponseEntity<Set<LikeDislike>>(post.getLikesDislikes(), HttpStatus.OK);
					}
				}

			}
		}
		
		LikeDislike likeDislike = new LikeDislike();
		likeDislike.setLike(isLike);
		
		User user = userRepository.findById(userId).get();
		likeDislike.setUser(user);
		
		post.getLikesDislikes().add(likeDislike);
		postRepository.save(post);
		
		return new ResponseEntity<Set<LikeDislike>>(post.getLikesDislikes(),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Set<Post>> getLikedPosts(Long id) {
		Set<Post> response = new HashSet<Post>();
		for(Post post : postRepository.findAll()) {
			LikeDislike ld = isLikedOrDisliked(post, id);
			if(ld != null) {
				if(ld.isLike()) {
					response.add(post);
				}
			}
		}
		return new ResponseEntity<Set<Post>>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Set<Post>> getDislikedPosts(Long id) {
		Set<Post> response = new HashSet<Post>();
		for(Post post : postRepository.findAll()) {
			LikeDislike ld = isLikedOrDisliked(post, id);
			if(ld != null) {
				if(!ld.isLike()) {
					response.add(post);
				}
			}
		}
		return new ResponseEntity<Set<Post>>(response, HttpStatus.OK);
	}
	
	private LikeDislike isLikedOrDisliked(Post post, Long userId) {
		for(LikeDislike ld : post.getLikesDislikes()) {
			if(ld.getUser().getId() == userId) {
				return ld;
			}
		}
		return null;
	}
	
}
