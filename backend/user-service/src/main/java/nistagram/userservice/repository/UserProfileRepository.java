package nistagram.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.userservice.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>  {

}
