package nistagram.storyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.storyservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
