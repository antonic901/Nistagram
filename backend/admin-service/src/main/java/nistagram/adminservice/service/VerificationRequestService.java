package nistagram.adminservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	public ResponseEntity<String> requestVerification(Long id) {
		for(VerificationRequest request : verificationRepository.findAll()) {
			if(request.getUser().getId() == id) {
				if(!request.isProcessed()) {
					return new ResponseEntity<String>("waiting", HttpStatus.OK);
				}
			}
		}
		
		VerificationRequest verificationRequest = new VerificationRequest();
		verificationRequest.setProcessed(false);
		verificationRequest.setVerified(false);
		
		User user = userRepository.findById(id).get();
		verificationRequest.setUser(user);
		
		verificationRepository.save(verificationRequest);
		
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
}
