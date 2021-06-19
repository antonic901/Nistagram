package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
