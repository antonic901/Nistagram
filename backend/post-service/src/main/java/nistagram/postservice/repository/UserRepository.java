package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
