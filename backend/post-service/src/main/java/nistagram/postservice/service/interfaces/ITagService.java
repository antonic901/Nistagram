package nistagram.postservice.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.model.Tag;

public interface ITagService {
	ResponseEntity<String> createTagsIfNotExist(List<String> tags);
	ResponseEntity<String> createTag(String name);
	Tag getTagByName(String name);
}
