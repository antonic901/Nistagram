package nistagram.postservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.dto.AddCommentDTO;
import nistagram.postservice.model.Comment;

public interface ICommentService {
	ResponseEntity<Comment> addComment(AddCommentDTO addCommentDTO);
}
