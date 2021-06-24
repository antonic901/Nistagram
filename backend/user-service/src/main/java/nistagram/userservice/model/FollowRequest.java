package nistagram.userservice.model;

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
@Table(name = "followrequests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FollowRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private boolean isActive;
	
	@OneToOne
	private UserProfile userWhoRequested;
	
	@OneToOne
	private UserProfile userWhoAccept;
	
}
