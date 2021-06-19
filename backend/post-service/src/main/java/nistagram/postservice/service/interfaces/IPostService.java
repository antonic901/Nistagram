package nistagram.postservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.dto.NewPostDTO;

public interface IPostService {
	public ResponseEntity<String> addNewPost(NewPostDTO newPostDTO);
}
