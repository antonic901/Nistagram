package nistagram.adminservice.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "verificationrequests")
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
	
}
