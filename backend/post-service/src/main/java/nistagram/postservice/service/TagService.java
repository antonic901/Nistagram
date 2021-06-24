package nistagram.postservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.postservice.model.Tag;
import nistagram.postservice.repository.TagRepository;
import nistagram.postservice.service.interfaces.ITagService;

@Service
public class TagService implements ITagService {
	
	private TagRepository tagRepository;
	
	@Autowired
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public ResponseEntity<String> createTagsIfNotExist(List<String> tags) {
		for(String tag : tags) {
			if(!exist(tag)) {
				Tag newTag = new Tag();
				newTag.setName(tag);
				tagRepository.save(newTag);
			}
		}
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> createTag(String name) {
		if(!exist(name)) {
			Tag newTag = new Tag();
			newTag.setName(name);
			tagRepository.save(newTag);
		}
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	private boolean exist(String tag) {
		List<Tag> existingTags = tagRepository.findAll();
		for(Tag t : existingTags) {
			if(t.getName().toLowerCase().equals(tag.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Tag getTagByName(String name) {
		for(Tag tag : tagRepository.findAll()) {
			if(tag.getName().toLowerCase().equals(name.toLowerCase())) {
				return tag;
			}
		}
		return null;
	}
	
}
