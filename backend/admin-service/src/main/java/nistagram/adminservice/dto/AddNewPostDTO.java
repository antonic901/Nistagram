package nistagram.adminservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddNewPostDTO {
	
	private Long postId;
	
	private Long captionId;
	
	private String description;
	
	private List<String> imagesAndVideos;
	
}
