package com.jpacourse.rest;


import com.jpacourse.dto.VisitSimpleTO;
import com.jpacourse.service.PatientService;
import com.jpacourse.service.VisitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/patient/{id}")
    public List<VisitSimpleTO> getVisitByPatientId(@PathVariable Long id) {
        return visitService.getVisitsByPatientId(id);
    }
}
