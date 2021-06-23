package nistagram.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.adminservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
