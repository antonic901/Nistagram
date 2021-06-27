package nistagram.postservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private boolean isViewed;
	
	@Column
	private String description;
	
	@OneToOne
	private User sender;
	
	@OneToOne
	private User receiver;
	
	@OneToOne
	private Post post;
	
}
