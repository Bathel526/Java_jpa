package com.jpacourse.persistence.dao.impl;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    private final EntityManager entityManager;

    public PatientDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description){
        PatientEntity patientEntity = entityManager.find(PatientEntity.class, patientId);

        if (patientEntity == null) {
            throw new EntityNotFoundException(patientId);
        }

        DoctorEntity doctorEntity = entityManager.find(DoctorEntity.class, doctorId);
        if (doctorEntity == null) {
            throw new EntityNotFoundException(doctorId);
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setPatient(patientEntity);
        visitEntity.setDoctor(doctorEntity);
        visitEntity.setTime(time);
        visitEntity.setDescription(description);

        patientEntity.getVisits().add(visitEntity);
        entityManager.persist(visitEntity);
    }

}

