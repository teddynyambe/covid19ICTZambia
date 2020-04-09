package zm.gov.health.dashboard.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zm.gov.health.dashboard.backend.entity.Symptom;

public interface SymptomRepository extends JpaRepository<Symptom, Long> {
}
