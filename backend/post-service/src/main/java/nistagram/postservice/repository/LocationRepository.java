package nistagram.postservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.postservice.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
