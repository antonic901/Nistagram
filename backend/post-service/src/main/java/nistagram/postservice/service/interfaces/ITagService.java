package nistagram.postservice.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.model.Tag;

public interface ITagService {
	ResponseEntity<String> createTagIfNotExist(List<String> tags);
	Tag getTagByName(String name);
}
