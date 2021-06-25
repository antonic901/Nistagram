package nistagram.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nistagram.adminservice.service.AdminService;

@RestController
@RequestMapping(value = "api/admin")
public class AdminController {
	
	private AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@RequestMapping(value = "/create-admin/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> createAdmin(@PathVariable("id") Long id) {
		return adminService.createAdmin(id);
	}

}
