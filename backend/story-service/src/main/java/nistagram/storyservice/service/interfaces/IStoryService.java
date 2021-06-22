package nistagram.storyservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.storyservice.dto.NewStoryDTO;

public interface IStoryService {
	ResponseEntity<String> addNewStory(NewStoryDTO newStoryDTO);
}
