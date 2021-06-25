package nistagram.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApproveRequestDTO {
	
	private Long requestId;
	
	private boolean isApproved;
	
}
