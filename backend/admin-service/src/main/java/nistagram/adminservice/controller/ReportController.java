package nistagram.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.adminservice.dto.AddReportDTO;
import nistagram.adminservice.service.ReportService;

@RestController
@RequestMapping(value = "api/report")
public class ReportController {

	private ReportService reportService;
	
	@Autowired
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@PostMapping(value = "/add-report", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addReport(@RequestBody AddReportDTO addReportDTO) throws Exception {
		return reportService.addReport(addReportDTO);
	}
	
}
