package nistagram.postservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.postservice.dto.AddCommentDTO;
import nistagram.postservice.model.Comment;
import nistagram.postservice.model.Post;
import nistagram.postservice.model.Tag;
import nistagram.postservice.model.User;
import nistagram.postservice.repository.CommentRepository;
import nistagram.postservice.repository.PostRepository;
import nistagram.postservice.repository.UserRepository;
import nistagram.postservice.service.interfaces.ICommentService;

@Service
public class CommentService implements ICommentService {
	
	private CommentRepository commentRepository;
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	private TagService tagService;
	
	@Autowired
	public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, TagService tagService) {
		this.commentRepository = commentRepository;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.tagService = tagService;
	}

	@Override
	public ResponseEntity<Comment> addComment(AddCommentDTO addCommentDTO) {
		Comment comment = new Comment();
		comment.setContent(addCommentDTO.getContent());
		
		User user = userRepository.findById(addCommentDTO.getUserId()).get();
		comment.setUser(user);
		
		comment.setTimeAndDate(LocalDateTime.now());
		
		for(String tag : addCommentDTO.getTags()) {
			Tag t = tagService.getTagByName(tag);
			comment.getTags().add(t);
		}
		
		comment = commentRepository.save(comment);
		
		Post post = postRepository.findById(addCommentDTO.getPostId()).get();
		post.getComments().add(comment);
		postRepository.save(post);
		
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}
	
}
