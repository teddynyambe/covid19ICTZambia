package zm.gov.health.dashboard.backend.repository;

import zm.gov.health.dashboard.backend.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Result findTopByOrderByReportDateDesc();
}
