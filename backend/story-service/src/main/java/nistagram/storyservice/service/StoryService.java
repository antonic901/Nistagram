package nistagram.storyservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.storyservice.dto.NewStoryDTO;
import nistagram.storyservice.model.Story;
import nistagram.storyservice.model.User;
import nistagram.storyservice.repository.StoryRepository;
import nistagram.storyservice.repository.UserRepository;
import nistagram.storyservice.service.interfaces.IStoryService;

@Service
public class StoryService implements IStoryService  {
	
	StoryRepository storyRepository;
	UserRepository userRepository;
	
	@Autowired
	public StoryService(StoryRepository storyRepository, UserRepository userRepository) {
		this.storyRepository = storyRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<String> addNewStory(NewStoryDTO newStoryDTO) {
		User user = userRepository.findById(newStoryDTO.getUserId()).get();
		Story newStory = new Story();
		newStory.setForClosedFriends(newStoryDTO.isForClosedFriends());
		newStory.setTimeAndDate(LocalDateTime.now());
		newStory.setUser(user);
		newStory.setImagesAndVideos(newStoryDTO.getImagesAndVideos());
		storyRepository.save(newStory);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
