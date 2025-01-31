package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void addVisitToPatient(Long PatientId, Long DoctorId, LocalDateTime time, String description);
}
