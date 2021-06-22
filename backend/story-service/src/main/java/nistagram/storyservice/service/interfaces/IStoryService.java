package nistagram.storyservice.service.interfaces;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import nistagram.storyservice.dto.NewStoryDTO;
import nistagram.storyservice.model.Story;

public interface IStoryService {
	ResponseEntity<String> addNewStory(NewStoryDTO newStoryDTO);
	ResponseEntity<Set<Story>> getStoriesForFeed(Long id);
}
