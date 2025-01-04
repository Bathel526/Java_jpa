package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.List;

public interface PatientService
{
    void deletePatientById(Long patientId);

    PatientTO findPatientById(final Long patientId);

    List<PatientTO> findAllPatients();


    PatientTO addPatient(PatientTO patientTO);
}
