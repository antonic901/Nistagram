package nistagram.postservice.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Caption caption;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
	private User user;
	
	@Column(name = "location", nullable = true)
	@Embedded
	private Location location;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Comment> comments = new HashSet<Comment>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<LikeDislike> likesDislikes = new HashSet<LikeDislike>();
	
	@ElementCollection
	private List<String> imagesAndVideos = new ArrayList<String>();
	
}
