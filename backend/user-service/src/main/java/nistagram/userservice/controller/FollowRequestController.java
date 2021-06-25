package nistagram.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.userservice.dto.ApproveRequestDTO;
import nistagram.userservice.service.FollowRequestService;

@RestController
@RequestMapping(value = "api/followrequest")
public class FollowRequestController {
	
	private FollowRequestService followRequestService;

	@Autowired
	public FollowRequestController(FollowRequestService followRequestService) {
		this.followRequestService = followRequestService;
	}
	
	@PostMapping(value = "/approve-request", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> approveRequest(@RequestBody ApproveRequestDTO approveRequestDTO) throws Exception {
		return followRequestService.approveRequest(approveRequestDTO.getRequestId(), approveRequestDTO.isApproved());
	}

}
