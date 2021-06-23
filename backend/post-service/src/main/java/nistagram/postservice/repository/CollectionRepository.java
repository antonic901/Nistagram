package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
	
}
