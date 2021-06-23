package nistagram.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.adminservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
