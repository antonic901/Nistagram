package nistagram.postservice.service.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.dto.NewPostDTO;
import nistagram.postservice.dto.SearchDTO;
import nistagram.postservice.model.LikeDislike;
import nistagram.postservice.model.Post;

public interface IPostService {
	ResponseEntity<String> addNewPost(NewPostDTO newPostDTO);
	ResponseEntity<List<Post>> searchByHashTag(SearchDTO searchDTO);
	ResponseEntity<List<Post>> searchByLocation(SearchDTO searchDTO);
	ResponseEntity<Set<Post>> getPostsForFeed(Long id);
	ResponseEntity<Set<LikeDislike>> addLikeOrDislike(Long postId, Long userId, Boolean isLike);
}
