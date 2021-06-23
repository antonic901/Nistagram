package nistagram.storyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.storyservice.model.HighLight;

public interface HighLightRepository extends JpaRepository<HighLight, Long> {

}
