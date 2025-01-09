package com.jpacourse.rest;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController
{
    private final PatientService patientService;

    public PatientController(PatientService patientService) {this.patientService = patientService;}


    @GetMapping
    public List<PatientTO> getAllPatients() {
        return patientService.findAllPatients();
    }

    @GetMapping("/{id}")
    PatientTO getPatientById (@PathVariable final Long id) {
        final PatientTO patientTO = patientService.findPatientById(id);
        if (patientTO == null) {
            throw new EntityNotFoundException(id);
        }
        return patientTO;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePatientById(@PathVariable final Long id) {
        if (patientService.findPatientById(id) == null) {
            throw new EntityNotFoundException(id);
        }
        patientService.deletePatientById(id);
    }
}
