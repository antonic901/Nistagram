package nistagram.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.userservice.model.FollowRequest;
import nistagram.userservice.model.UserProfile;
import nistagram.userservice.repository.FollowRequestRepository;
import nistagram.userservice.repository.UserProfileRepository;
import nistagram.userservice.service.interfaces.IFollowRequestService;

@Service
public class FollowRequestService implements IFollowRequestService {
	
	private FollowRequestRepository followRequestRepository;
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	public FollowRequestService(FollowRequestRepository followRequestRepository, UserProfileRepository userProfileRepository) {
		this.followRequestRepository = followRequestRepository;
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public ResponseEntity<String> approveRequest(Long requestId, boolean isAproved) {
		FollowRequest followRequest = followRequestRepository.findById(requestId).get();
		if(isAproved) {
			UserProfile userWhoRequest = userProfileRepository.findById(followRequest.getUserWhoRequested().getId()).get();
			UserProfile userWhoAccept = userProfileRepository.findById(followRequest.getUserWhoAccept().getId()).get();
			
			userWhoRequest.getFollowers().add(userWhoAccept);
			userWhoRequest.getFollowing().add(userWhoAccept);
			
			userWhoAccept.getFollowers().add(userWhoRequest);
			userWhoAccept.getFollowing().add(userWhoRequest);
			
			userProfileRepository.save(userWhoRequest);
			userProfileRepository.save(userWhoAccept);
			
			followRequest.setActive(false);
			followRequestRepository.save(followRequest);
			
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		else {
			followRequest.setActive(false);
			followRequestRepository.save(followRequest);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}
	
	
	
	
}
