package nistagram.adminservice.service.interfaces;

import org.springframework.http.ResponseEntity;

import nistagram.adminservice.dto.AddReportDTO;

public interface IReportService {
	ResponseEntity<String> addReport(AddReportDTO addReportDTO); 
}
