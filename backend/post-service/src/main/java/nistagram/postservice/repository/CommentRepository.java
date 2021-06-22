package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
