package nistagram.postservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.dto.NewPostDTO;

public interface IPostService {
	ResponseEntity<String> addNewPost(NewPostDTO newPostDTO);
}
