package nistagram.postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.dto.AddCommentDTO;
import nistagram.postservice.model.Comment;
import nistagram.postservice.service.CommentService;

@RestController
@RequestMapping(value = "api/comment")
public class CommentController {
	
	private CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping(value = "/add-new-comment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comment> addNewComment(@RequestBody AddCommentDTO addCommentDTO) throws Exception {
		return commentService.addComment(addCommentDTO);
	}
	
}
