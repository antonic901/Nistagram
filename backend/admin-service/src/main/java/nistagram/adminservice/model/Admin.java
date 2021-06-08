package nistagram.adminservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<VerificationRequest> verificationRequests = new HashSet<VerificationRequest>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Report> reports = new HashSet<Report>();
	
}
