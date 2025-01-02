package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

public interface PatientService
{
    public void deletePatientById(Long patientId);

    public PatientTO findPatientById(final Long patientId);
}
