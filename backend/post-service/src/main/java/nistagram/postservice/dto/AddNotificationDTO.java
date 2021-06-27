package nistagram.postservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddNotificationDTO {

	private String description;

	private Long sender;

	private Long receiver;

	private Long post;
	
}
