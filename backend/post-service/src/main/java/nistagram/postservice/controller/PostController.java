package nistagram.postservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.dto.NewPostDTO;
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
	
}
