package nistagram.storyservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Story {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean isForClosedFriends;
	
	@Column(nullable=false)
	private LocalDateTime timeAndDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userprofile_id", nullable = true)
	private User user;
	
	//pitati asistenta
	@ElementCollection
	private List<String> imagesAndVideos;

}
