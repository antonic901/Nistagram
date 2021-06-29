package nistagram.postservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPostDTO {
	
	private LocationDTO location;
	
	private Long userId;
	
	private String description;
	
	private List<String> hashTags;
	
	private List<String> profileTags;
	
	private List<String> imagesAndVideos;
	
}
