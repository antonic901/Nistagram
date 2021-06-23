package nistagram.storyservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.storyservice.dto.AddToHighLightDTO;
import nistagram.storyservice.service.HighLightService;

@RestController
@RequestMapping(value = "api/highlight")
public class HighLightController {

	private HighLightService highLightService;
	
	@Autowired
	public HighLightController(HighLightService highLightService) {
		this.highLightService = highLightService;
	}
	
	@PostMapping(value = "/add-to-highlight", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCollection(@RequestBody AddToHighLightDTO addToHighLight) throws Exception {
		return highLightService.addToHighLight(addToHighLight.getCollectionId(), addToHighLight.getPostId());
	}
	
}
