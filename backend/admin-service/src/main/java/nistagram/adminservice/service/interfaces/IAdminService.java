package nistagram.adminservice.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IAdminService {
	ResponseEntity<String> createAdmin(Long id);
}
