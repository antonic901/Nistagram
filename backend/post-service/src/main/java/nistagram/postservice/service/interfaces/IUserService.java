package nistagram.postservice.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IUserService {
	ResponseEntity<String> createUser(Long id);
}
