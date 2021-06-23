package nistagram.adminservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.adminservice.dto.AddNewPostDTO;

public interface IPostService {
	ResponseEntity<String> createPost(AddNewPostDTO newPostDTO);
}
