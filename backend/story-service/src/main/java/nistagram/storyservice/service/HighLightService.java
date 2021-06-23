package nistagram.storyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.storyservice.model.HighLight;
import nistagram.storyservice.model.Story;
import nistagram.storyservice.repository.HighLightRepository;
import nistagram.storyservice.repository.StoryRepository;
import nistagram.storyservice.service.interfaces.IHighLightService;

@Service
public class HighLightService implements IHighLightService {

	private HighLightRepository highLightRepository;
	private StoryRepository storyRepository;
	
	@Autowired
	public HighLightService(HighLightRepository highLightRepository, StoryRepository storyRepository) {
		this.highLightRepository = highLightRepository;
		this.storyRepository = storyRepository;
	}

	@Override
	public ResponseEntity<String> addToHighLight(Long highLightId, Long storyId) {
		HighLight highLight = highLightRepository.findById(highLightId).get();
		for(Story story : highLight.getStories()) {
			if(story.getId() == storyId) {
				return new ResponseEntity<String>("exist", HttpStatus.OK);
			}
		}
		highLight.getStories().add(storyRepository.findById(storyId).get());
		highLightRepository.save(highLight);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	
	
}
