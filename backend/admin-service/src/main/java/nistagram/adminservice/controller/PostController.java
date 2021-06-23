package nistagram.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.adminservice.dto.AddNewPostDTO;
import nistagram.adminservice.service.PostService;

@RestController
@RequestMapping(value = "api/post")
public class PostController {
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping(value = "/create-post", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPost(@RequestBody AddNewPostDTO newPostDTO) throws Exception {
		return postService.createPost(newPostDTO);
	}
	
}
