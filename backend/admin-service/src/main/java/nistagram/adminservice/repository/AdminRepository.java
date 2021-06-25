package nistagram.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.adminservice.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
