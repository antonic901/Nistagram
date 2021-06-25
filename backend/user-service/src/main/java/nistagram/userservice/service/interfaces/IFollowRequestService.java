package nistagram.userservice.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IFollowRequestService {
	ResponseEntity<String> approveRequest(Long requestId, boolean isAproved);
}
