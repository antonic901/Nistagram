package nistagram.postservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.postservice.model.Location;
import nistagram.postservice.service.LocationService;

@RestController
@RequestMapping(value = "api/location")
public class LocationController {
	
	private LocationService locationService;
	
	@Autowired
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@RequestMapping(value = "/search-location/{input}", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> searchLocation(@PathVariable("input") String input) {
		return locationService.searchLocation(input);
	}
	
}
