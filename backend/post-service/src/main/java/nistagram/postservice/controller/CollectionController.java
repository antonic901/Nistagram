package nistagram.postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.dto.AddToCollectionDTO;
import nistagram.postservice.service.CollectionService;

@RestController
@RequestMapping(value = "api/collection")
public class CollectionController {
	
	private CollectionService collectionService;
	
	@Autowired
	public CollectionController(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
	@PostMapping(value = "/add-to-collection", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCollection(@RequestBody AddToCollectionDTO addToCollectionDTO) throws Exception {
		return collectionService.addToCollection(addToCollectionDTO.getCollectionId(), addToCollectionDTO.getPostId(), addToCollectionDTO.getUserId());
	}
	
}
