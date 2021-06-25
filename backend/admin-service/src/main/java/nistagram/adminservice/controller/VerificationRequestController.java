package nistagram.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.adminservice.dto.ProceedVerificationDTO;
import nistagram.adminservice.dto.RequestVerificationDTO;
import nistagram.adminservice.model.VerificationRequest;
import nistagram.adminservice.service.VerificationRequestService;

@RestController
@RequestMapping(value = "api/verificationrequest")
public class VerificationRequestController {
	
	private VerificationRequestService verificationRequestService;

	@Autowired
	public VerificationRequestController(VerificationRequestService verificationRequestService) {
		this.verificationRequestService = verificationRequestService;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<VerificationRequest>> getAll() {
		return verificationRequestService.gettAll();
	}
	
	@PostMapping(value = "/request-verification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> requestVerification(@RequestBody RequestVerificationDTO requestVerificationDTO) throws Exception {
		return verificationRequestService.requestVerification(requestVerificationDTO);
	}
	
	@RequestMapping(value = "/check-status-of-verification/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> checkStatusOfVerification(@PathVariable("id") Long id) {
		return verificationRequestService.checkStatusOfVerification(id);
	}
	
	@PostMapping(value = "/proceed-verification", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VerificationRequest>> proceedVerification(@RequestBody ProceedVerificationDTO proceedVerificationDTO) throws Exception {
		return verificationRequestService.proceedRequest(proceedVerificationDTO.getId(), proceedVerificationDTO.isApproved());
	}

}
