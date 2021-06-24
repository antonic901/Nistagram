package nistagram.adminservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nistagram.adminservice.model.enums.CategoryType;

@Table(name = "verificationrequests")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private boolean isProcessed;
	
	@Column
	private boolean isVerified;
	
	@OneToOne
	private User user;

	@Column(name = "categorytype", nullable = false)
	@Enumerated(EnumType.STRING)
	CategoryType category;
	
	@Column
	private String imagesAndVideos;
}
