package zm.gov.health.dashboard.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zm.gov.health.dashboard.backend.entity.Result;
import zm.gov.health.dashboard.backend.entity.Symptom;
import zm.gov.health.dashboard.backend.repository.ResultRepository;

import java.sql.SQLException;

@RestController
@ResponseBody
public class Covi19ResultController {
    private ResultService resultService;
    private SymptomService symptomService;

    public Covi19ResultController(ResultService resultService, SymptomService symptomService) {
        this.resultService = resultService;
        this.symptomService = symptomService;
    }

    @GetMapping(value = "/covid19", produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Result> covid19(@RequestParam(value = "token") String token) {
        return new ResponseEntity<Result>(resultService.getLatestResult(), HttpStatus.OK);
    }

    @PostMapping(value = "/covid19/symptom/submit", consumes = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE
    })
    public String saveResults(@RequestBody Symptom symptom, @RequestParam(value = "token") String token) {
        System.out.println("You entered: " + symptom.getFever() + " and " +symptom.getMuscleJointPain());
        symptomService.save(symptom);
        return "saved";
    }
}
