package nistagram.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
