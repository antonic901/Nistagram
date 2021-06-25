package nistagram.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProceedVerificationDTO {
	
	private Long id;
	
	private boolean isApproved;
	
}
