package nistagram.postservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.dto.AddLikeDislikeDTO;
import nistagram.postservice.dto.NewPostDTO;
import nistagram.postservice.dto.SearchDTO;
import nistagram.postservice.model.LikeDislike;
import nistagram.postservice.model.Post;
import nistagram.postservice.service.PostService;

@RestController
@RequestMapping(value = "api/post")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping(value = "/add-new-post", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addNewPost(@RequestBody NewPostDTO newPostDTO) throws Exception {
		return postService.addNewPost(newPostDTO);
	}
	
	@PostMapping(value = "/search-by-hashtag", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> searchByHashTag(@RequestBody SearchDTO searchDTO) throws Exception
	{
		return postService.searchByHashTag(searchDTO);
	}
	
	@PostMapping(value = "/search-by-location", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> searchByLocation(@RequestBody SearchDTO searchDTO) throws Exception
	{
		return postService.searchByLocation(searchDTO);
	}
	
	@RequestMapping(value = "/get-posts-for-feed/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Post>> createUser(@PathVariable("id") Long id) {
		return postService.getPostsForFeed(id);
	}
	
	@PostMapping(value = "/add-like-dislike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<LikeDislike>> searchLikeOrDislike(@RequestBody AddLikeDislikeDTO addLikeDislikeDTO) throws Exception
	{
		return postService.addLikeOrDislike(addLikeDislikeDTO.getPostId(), addLikeDislikeDTO.getUserId(), addLikeDislikeDTO.isLike());
	}
	
	@RequestMapping(value = "/get-liked-posts/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Post>> getLikedPosts(@PathVariable("id") Long id) {
		return postService.getLikedPosts(id);
	}
	
	@RequestMapping(value = "/get-disliked-posts/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Post>> getDislikedPosts(@PathVariable("id") Long id) {
		return postService.getDislikedPosts(id);
	}
	
}
