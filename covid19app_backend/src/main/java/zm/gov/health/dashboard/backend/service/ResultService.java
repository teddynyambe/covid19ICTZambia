package zm.gov.health.dashboard.backend.service;

import zm.gov.health.dashboard.backend.entity.Result;
import zm.gov.health.dashboard.backend.repository.ResultRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class ResultService {

    private ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    public void delete(Result result) {
        resultRepository.delete(result);
    }

    public void save(Result result) {
        resultRepository.save(result);
    }

    public Result getLatestResult() {
        return resultRepository.findTopByOrderByReportDateDesc();
    }

    @PostConstruct
    public void populateTestData() {

        if (findAll().size() == 0) {
            Random r = new Random(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

            Result result = new Result();

            result.setReportDate(LocalDate.parse("1-04-2020", formatter));
            result.setConfirmedZambia(39);
            result.setConfirmedWorld(new Random().nextInt(1260330));
            result.setDeathsZambia(1);
            result.setDeathsWorld(new Random().nextInt(60890));
            result.setRecoveredZambia(3);
            result.setRecoveredWorld(new Random().nextInt(299889));
            result.setTestedPositive(new Random().nextInt(90));
            result.setTestedNegative(new Random().nextInt(100));

            resultRepository.save(result);

            result = new Result();

            result.setReportDate(LocalDate.parse("3-04-2020", formatter));
            result.setConfirmedZambia(39);
            result.setConfirmedWorld(new Random().nextInt(56777890));
            result.setDeathsZambia(1);
            result.setDeathsWorld(new Random().nextInt(608290));
            result.setRecoveredZambia(5);
            result.setRecoveredWorld(new Random().nextInt(2998889));
            result.setTestedPositive(new Random().nextInt(902));
            result.setTestedNegative(new Random().nextInt(1001));

            resultRepository.save(result);

            result = new Result();

            result.setReportDate(LocalDate.parse("5-04-2020", formatter));
            result.setConfirmedZambia(39);
            result.setConfirmedWorld(new Random().nextInt(567777890));
            result.setDeathsZambia(1);
            result.setDeathsWorld(new Random().nextInt(608290));
            result.setRecoveredZambia(7);
            result.setRecoveredWorld(new Random().nextInt(2998889));
            result.setTestedPositive(new Random().nextInt(922));
            result.setTestedNegative(new Random().nextInt(1021));

            resultRepository.save(result);
        }
    }
}
//            resultRepository.saveAll(
//
//                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
//                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
//                            "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
//                            "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
//                            "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
//                            "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
//                            "Jaydan Jackson", "Bernard Nilsen")
//                            .map(name -> {
//                                String[] split = name.split(" ");
//                                Contact contact = new Contact();
//                                contact.setFirstName(split[0]);
//                                contact.setLastName(split[1]);
//                                contact.setCompany(companies.get(r.nextInt(companies.size())));
//                                contact.setStatus(Contact.Status.values()[r.nextInt(Contact.Status.values().length)]);
//                                String email = (contact.getFirstName() + "." + contact.getLastName() + "@" + contact.getCompany().getName().replaceAll("[\\s-]", "") + ".com").toLowerCase();
//                                contact.setEmail(email);
//                                return contact;
//                            }).collect(Collectors.toList()));
//

