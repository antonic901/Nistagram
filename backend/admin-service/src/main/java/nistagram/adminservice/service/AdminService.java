package nistagram.adminservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import nistagram.adminservice.model.Admin;
import nistagram.adminservice.repository.AdminRepository;
import nistagram.adminservice.service.interfaces.IAdminService;

@Service
public class AdminService implements IAdminService {

	private AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public ResponseEntity<String> createAdmin(Long id) {
		Admin admin = new Admin();
		admin.setId(id);
		adminRepository.save(admin);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
	
}
