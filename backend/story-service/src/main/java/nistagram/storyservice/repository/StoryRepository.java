package nistagram.storyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.storyservice.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {

}
