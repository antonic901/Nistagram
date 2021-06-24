package nistagram.postservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddLikeDislikeDTO {
	
	private Long userId;
	
	private Long postId;
	
	private boolean isLike;
	
}
