package nistagram.postservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.postservice.model.Location;
import nistagram.postservice.repository.LocationRepository;
import nistagram.postservice.service.interfaces.ILocationService;

@Service
public class LocationService implements ILocationService {
	
	LocationRepository locationRepository;
	
	@Autowired
	public LocationService(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	@Override
	public ResponseEntity<List<Location>> searchLocation(String input) {
		List<Location> locations = locationRepository.findAll();
		List<Location> returnLocations = new ArrayList<Location>();
		for(Location location : locations) {
			String l = location.getCountry().toLowerCase() + ", " + location.getCity().toLowerCase() + ", " + location.getStreet().toLowerCase();
			if(l.contains(input.toLowerCase())) {
				returnLocations.add(location);
			}
		}
		return new ResponseEntity<List<Location>>(returnLocations, HttpStatus.OK);
	}
	
}
