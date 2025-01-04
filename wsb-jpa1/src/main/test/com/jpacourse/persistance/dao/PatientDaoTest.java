package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Gender;
import com.jpacourse.persistence.enums.Specialization;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private EntityManager entityManager;

    private PatientEntity patient;
    private DoctorEntity doctor;

    @Transactional
    @BeforeEach
    void setUp() {
        // Tworzenie pacjenta i lekarza do testów
        patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setEmail("john.doe@example.com");
        patient.setTelephoneNumber("987654321");
        patient.setDateOfBirth(LocalDateTime.of(1980, 1, 1, 0, 0).toLocalDate());
        patient.setGender(Gender.MALE);
        patient.setPatientNumber("12345");
        if (patient.getVisits() == null) {
            patient.setVisits(new ArrayList<>());
        }

        doctor = new DoctorEntity();
        doctor.setFirstName("Dr. Smith");
        doctor.setLastName("Jones");
        doctor.setSpecialization(Specialization.GP);
        doctor.setDoctorNumber("D123");
        doctor.setEmail("dr.smith.jones@example.com");
        doctor.setTelephoneNumber("123456789");

        // Persistowanie pacjenta i lekarza w bazie
        entityManager.persist(patient);
        entityManager.persist(doctor);
    }

    @Transactional
    @Test
    void testAddVisitToPatient_shouldAddVisitSuccessfully() {
        // Given
        LocalDateTime visitTime = LocalDateTime.now();
        String description = "Routine check-up";

        // When
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), visitTime, description);

        // Then
        // Sprawdzenie, czy wizyta została dodana do pacjenta
        PatientEntity updatedPatient = entityManager.find(PatientEntity.class, patient.getId());
        assertThat(updatedPatient.getVisits()).hasSize(1);
        VisitEntity addedVisit = updatedPatient.getVisits().get(0);
        assertThat(addedVisit.getDescription()).isEqualTo(description);
        assertThat(addedVisit.getDoctor().getId()).isEqualTo(doctor.getId());
    }

    @Transactional
    @Test
    void testAddVisitToPatient_shouldThrowExceptionWhenPatientNotFound() {
        // Given
        Long invalidPatientId = 999L;  // Nieistniejący pacjent
        LocalDateTime visitTime = LocalDateTime.now();
        String description = "Routine check-up";

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> {
            patientDao.addVisitToPatient(invalidPatientId, doctor.getId(), visitTime, description);
        });
    }

    @Transactional
    @Test
    void testAddVisitToPatient_shouldThrowExceptionWhenDoctorNotFound() {
        // Given
        Long invalidDoctorId = 999L;  // Nieistniejący lekarz
        LocalDateTime visitTime = LocalDateTime.now();
        String description = "Routine check-up";

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> {
            patientDao.addVisitToPatient(patient.getId(), invalidDoctorId, visitTime, description);
        });
    }
}