package nistagram.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.adminservice.model.enums.ReportType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddReportDTO {
	
	private Long userId;
	
	private Long postId;
	
	private ReportType reason;
	
}
