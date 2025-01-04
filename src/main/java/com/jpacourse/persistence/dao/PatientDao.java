package com.jpacourse.persistence.dao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void addVisitToPatient(Long PatientId, Long DoctorId, LocalDateTime time, String description);

    List<PatientEntity> findPatientsByLastName(String lastName);

    List<PatientEntity> findPatientsThatHaveMoreThanXVisits(Long x);

    List<PatientEntity> findPatientsByGender(Gender gender);
}
