package nistagram.adminservice.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IVerificationRequestService {
	ResponseEntity<String> requestVerification(Long id);
}
