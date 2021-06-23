package nistagram.adminservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nistagram.adminservice.model.enums.ReportType;

@RestController
@RequestMapping(value = "api/reporttype")
public class ReportTypeController {
	
	@GetMapping("/get-report-types")
	public ResponseEntity<List<ReportType>> getReportTypes() {
		List<ReportType> response = new ArrayList<ReportType>(Arrays.asList(ReportType.values()));
		return new ResponseEntity<List<ReportType>>(response, HttpStatus.OK);
	}
}
