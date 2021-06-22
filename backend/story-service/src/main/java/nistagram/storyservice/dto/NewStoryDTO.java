package nistagram.storyservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewStoryDTO {
	
	private Long userId;
	
	private boolean forClosedFriends;
	
	private List<String> imagesAndVideos;
	
}
