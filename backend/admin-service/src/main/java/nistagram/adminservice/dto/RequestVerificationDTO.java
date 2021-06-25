package nistagram.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.adminservice.model.enums.CategoryType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestVerificationDTO {

	private Long userId;
	
	private String image;
	
	private CategoryType category;
	
}
