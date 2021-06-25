package nistagram.adminservice.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import nistagram.adminservice.dto.RequestVerificationDTO;
import nistagram.adminservice.model.VerificationRequest;

public interface IVerificationRequestService {
	ResponseEntity<List<VerificationRequest>> gettAll();
	ResponseEntity<String> requestVerification(RequestVerificationDTO requestVerificationDTO);
	ResponseEntity<String> checkStatusOfVerification(Long id);
	ResponseEntity<List<VerificationRequest>> proceedRequest(Long id, boolean isApproved);
}
