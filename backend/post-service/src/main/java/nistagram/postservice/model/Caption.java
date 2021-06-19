package nistagram.postservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "captions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@ManyToMany
    @JoinTable
    (
        name = "tag_caption",
        joinColumns = @JoinColumn(name = "caption_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
	private Set<Tag> tags = new HashSet<Tag>();
	
	@OneToOne(mappedBy = "caption")
	private Post post;
}
