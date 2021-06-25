package nistagram.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.userservice.model.FollowRequest;

public interface FollowRequestRepository extends JpaRepository<FollowRequest, Long> {

}
