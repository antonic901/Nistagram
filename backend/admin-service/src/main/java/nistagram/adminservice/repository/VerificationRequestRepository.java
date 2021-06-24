package nistagram.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.adminservice.model.VerificationRequest;

public interface VerificationRequestRepository extends JpaRepository<VerificationRequest, Long> {

}
