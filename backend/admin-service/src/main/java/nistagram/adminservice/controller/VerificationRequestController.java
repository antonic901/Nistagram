package nistagram.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.adminservice.service.VerificationRequestService;

@RestController
@RequestMapping(value = "api/verificationrequest")
public class VerificationRequestController {
	
	private VerificationRequestService verificationRequestService;

	@Autowired
	public VerificationRequestController(VerificationRequestService verificationRequestService) {
		this.verificationRequestService = verificationRequestService;
	}
	
	@RequestMapping(value = "/request-verification/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> requestVerification(@PathVariable("id") Long id) {
		return verificationRequestService.requestVerification(id);
	}

}
