package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.LikeDislike;

public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Long> {

}
