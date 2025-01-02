package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Gender;
import com.jpacourse.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PatientServiceImplTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    private PatientEntity patient;

    @Test
    void getShouldFindPatientByID() {
        PatientEntity patientEntity = patientDao.findOne(1L);
        // then
        assertThat(patientEntity).isNotNull();
        assertThat(patientEntity.getFirstName()).isEqualTo("Alice");
    }

    @Test
    void testDeletePatientById_shouldDeletePatientAndVisits() {
        // Given (patient)

        patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setEmail("john.doe@example.com");
        patient.setTelephoneNumber("123456789");
        patient.setDateOfBirth(LocalDate.of(1980, 1, 1));
        patient.setGender(Gender.valueOf("MALE"));
        patient.setPatientNumber("123456789");

        // Given (visits)
        VisitEntity visit1 = new VisitEntity();
        visit1.setDescription("First visit");
        visit1.setTime(LocalDateTime.now());
        visit1.setPatient(patient);

        VisitEntity visit2 = new VisitEntity();
        visit2.setDescription("Second visit");
        visit2.setTime(LocalDateTime.now().plusDays(1));
        visit2.setPatient(patient);

        // when
        patient.setVisits(List.of(visit1, visit2));
        patientDao.save(patient);

        patientService.deletePatientById(patient.getId());

        // then
        assertThat(patientDao.findOne(patient.getId())).isNull();
        assertThat(visitDao.findOne(patient.getVisits().get(0).getId())).isNull();
        assertThat(visitDao.findOne(patient.getVisits().get(1).getId())).isNull();
    }
}