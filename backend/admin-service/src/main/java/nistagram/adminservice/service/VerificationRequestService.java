package nistagram.adminservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.adminservice.dto.RequestVerificationDTO;
import nistagram.adminservice.model.User;
import nistagram.adminservice.model.VerificationRequest;
import nistagram.adminservice.repository.UserRepository;
import nistagram.adminservice.repository.VerificationRequestRepository;
import nistagram.adminservice.service.interfaces.IVerificationRequestService;

@Service
public class VerificationRequestService implements IVerificationRequestService {
	
	private VerificationRequestRepository verificationRepository;
	private UserRepository userRepository;

	public VerificationRequestService(VerificationRequestRepository verificationRepository, UserRepository userRepository) {
		this.verificationRepository = verificationRepository;
		this.userRepository = userRepository;
	}

	@Override
	public ResponseEntity<List<VerificationRequest>> gettAll() {
		List<VerificationRequest> response = new ArrayList<VerificationRequest>();
		for(VerificationRequest vr : verificationRepository.findAll()) {
			if(!vr.isDeleted()) {
				if(!vr.isProcessed()) {
					response.add(vr);
				}
			}
		}
		return new ResponseEntity<List<VerificationRequest>>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> requestVerification(RequestVerificationDTO requestVerificationDTO) {
		VerificationRequest verificationRequest = new VerificationRequest();
		verificationRequest.setProcessed(false);
		verificationRequest.setVerified(false);
		verificationRequest.setCategory(requestVerificationDTO.getCategory());
		verificationRequest.setImage(requestVerificationDTO.getImage());
		
		User user = userRepository.findById(requestVerificationDTO.getUserId()).get();
		verificationRequest.setUser(user);
		
		verificationRepository.save(verificationRequest);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> checkStatusOfVerification(Long id) {
		for(VerificationRequest request : verificationRepository.findAll()) {
			if(request.getUser().getId() == id) {
				if(!request.isDeleted()) {
					if(!request.isProcessed()) {
						return new ResponseEntity<String>("waiting", HttpStatus.OK);
					}
					else {
						if(request.isVerified()) {
							return new ResponseEntity<String>("verified", HttpStatus.OK);
						}
					}
				}
			}
		}
		return new ResponseEntity<String>("not_sended", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<VerificationRequest>> proceedRequest(Long id, boolean isApproved) {
		VerificationRequest verificationRequest = verificationRepository.findById(id).get();
		if(isApproved) {
			verificationRequest.setProcessed(true);
			verificationRequest.setVerified(true);
			verificationRequest.getUser().setVerified(isApproved);
		}
		else {
			verificationRequest.setDeleted(true);
			verificationRequest.setProcessed(true);
			verificationRequest.getUser().setVerified(isApproved);
		}
		
		verificationRepository.save(verificationRequest);
		return gettAll();
	}
}
