package nistagram.postservice.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.dto.NewPostDTO;
import nistagram.postservice.dto.SearchDTO;
import nistagram.postservice.model.Post;

public interface IPostService {
	ResponseEntity<String> addNewPost(NewPostDTO newPostDTO);
	ResponseEntity<List<Post>> searchByHashTag(SearchDTO searchDTO);
	ResponseEntity<List<Post>> searchByLocation(SearchDTO searchDTO);
}
