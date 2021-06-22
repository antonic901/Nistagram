package nistagram.storyservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.storyservice.dto.NewStoryDTO;
import nistagram.storyservice.model.Story;
import nistagram.storyservice.service.StoryService;

@RestController
@RequestMapping(value = "api/story")
public class StoryController {

	private StoryService storyService;
	
	@Autowired
	public StoryController(StoryService storyService) {
		this.storyService = storyService;
	}
	
	@PostMapping(value = "/add-new-story", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addNewStory(@RequestBody NewStoryDTO newStoryDTO) throws Exception {
		return storyService.addNewStory(newStoryDTO);
	}
	
	@RequestMapping(value = "/get-story-for-feed/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Story>> getStoriesForFeed(@PathVariable("id") Long id) {
		return storyService.getStoriesForFeed(id);
	}
	
}
