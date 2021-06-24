package nistagram.postservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentDTO {
	
	private Long userId;
	
	private Long postId;
	
	private String content;
	
	List<String> hashTags;
	
	List<String> profileTags;
	
}
