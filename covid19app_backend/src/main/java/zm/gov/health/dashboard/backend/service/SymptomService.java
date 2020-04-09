package zm.gov.health.dashboard.backend.service;

import org.springframework.stereotype.Service;
import zm.gov.health.dashboard.backend.entity.Symptom;
import zm.gov.health.dashboard.backend.repository.SymptomRepository;

import java.util.List;

@Service
public class SymptomService {
    private SymptomRepository symptomRepository;

    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public void save(Symptom symptom){
        symptomRepository.save(symptom);
    }

    public List<Symptom> findAll(){
        return symptomRepository.findAll();
    }
}
