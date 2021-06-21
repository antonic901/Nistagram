package nistagram.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckFollowDTO {
	
	private Long userPostId;
	
	private Long userViewId;
	
}
