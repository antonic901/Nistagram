package nistagram.postservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.dto.TagDTO;
import nistagram.postservice.model.Tag;
import nistagram.postservice.service.TagService;

@RestController
@RequestMapping(value = "api/tag")
public class TagController {

	private TagService tagService;
	
	@Autowired
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}
	
	@PostMapping(value = "/create-tag", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createTagIfNotExist(@RequestBody TagDTO tagDTO) throws Exception
	{
		return tagService.createTagsIfNotExist(tagDTO.getTags());
	}
	
	@RequestMapping(value = "/create-tag/{name}", method = RequestMethod.GET)
	public ResponseEntity<String> createTag(@PathVariable("name") String name) {
		return tagService.createTag(name);
	}
	
	@RequestMapping(value = "/get-tag/{name}", method = RequestMethod.GET)
	public ResponseEntity<Tag> getTagByName(@PathVariable("name") String name) {
		return new ResponseEntity<Tag>(tagService.getTagByName(name), HttpStatus.OK);
	}
	
}
