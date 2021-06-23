package nistagram.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nistagram.adminservice.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
