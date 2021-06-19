package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

}
