package nistagram.postservice.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import nistagram.postservice.model.Location;

public interface ILocationService {
	ResponseEntity<List<Location>> searchLocation(String input);
}
