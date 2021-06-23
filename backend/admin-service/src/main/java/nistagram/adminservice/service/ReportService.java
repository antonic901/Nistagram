package nistagram.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.adminservice.dto.AddReportDTO;
import nistagram.adminservice.model.Post;
import nistagram.adminservice.model.Report;
import nistagram.adminservice.model.User;
import nistagram.adminservice.repository.PostRepository;
import nistagram.adminservice.repository.ReportRepository;
import nistagram.adminservice.repository.UserRepository;
import nistagram.adminservice.service.interfaces.IReportService;

@Service
public class ReportService implements IReportService {

	private ReportRepository reportRepository;
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	@Autowired
	public ReportService(ReportRepository reportRepository, UserRepository userRepository, PostRepository postRepository) {
		this.reportRepository = reportRepository;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public ResponseEntity<String> addReport(AddReportDTO addReportDTO) {
		for(Report r : reportRepository.findAll()) {
			if(r.getReporter().getId() == addReportDTO.getUserId()) {
				return new ResponseEntity<String>("already_reported", HttpStatus.OK);
			}
		}
		
		Report report = new Report();
		
		User user = userRepository.findById(addReportDTO.getUserId()).get();
		report.setReporter(user);
		
		Post post = postRepository.findById(addReportDTO.getPostId()).get();
		report.setPost(post);
		
		report.setReason(addReportDTO.getReason());
		report.setReviewed(false);
		
		reportRepository.save(report);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
