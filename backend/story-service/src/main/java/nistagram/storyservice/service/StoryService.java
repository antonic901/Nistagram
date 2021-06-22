package nistagram.storyservice.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nistagram.storyservice.dto.CheckFollowDTO;
import nistagram.storyservice.dto.NewStoryDTO;
import nistagram.storyservice.model.Story;
import nistagram.storyservice.model.User;
import nistagram.storyservice.repository.StoryRepository;
import nistagram.storyservice.repository.UserRepository;
import nistagram.storyservice.service.interfaces.IStoryService;

@Service
public class StoryService implements IStoryService  {
	
	private StoryRepository storyRepository;
	private UserRepository userRepository;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public StoryService(StoryRepository storyRepository, UserRepository userRepository, RestTemplate restTemplate) {
		this.storyRepository = storyRepository;
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
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

	@Override
	public ResponseEntity<Set<Story>> getStoriesForFeed(Long id) {
		Set<Story> response = new HashSet<Story>();
		if(id == -1) {
			for(Story post : storyRepository.findAll()) {
				CheckFollowDTO checkFollowDTO = new CheckFollowDTO(post.getUser().getId(),id);
				Boolean isPrivate = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-private", checkFollowDTO, Boolean.class);
				if(!isPrivate) {
					response.add(post);
				}
			}
		}
		else {
			for(Story post : storyRepository.findAll()) {
				if(post.getUser().getId() != id) {
					CheckFollowDTO checkFollowDTO = new CheckFollowDTO(post.getUser().getId(),id);
					Boolean isUserFollowing = restTemplate.postForObject("http://localhost:8081/api/userprofile/is-followed-by", checkFollowDTO, Boolean.class);
					if(isUserFollowing) {
						response.add(post);
					}
				}
			}
		}
		return new ResponseEntity<Set<Story>>(response, HttpStatus.OK);
	}
	
}
