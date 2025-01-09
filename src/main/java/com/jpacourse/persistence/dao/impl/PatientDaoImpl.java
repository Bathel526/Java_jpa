package com.jpacourse.persistence.dao.impl;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Gender;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Autowired
    private DoctorDao doctorDao;


    @Transactional
    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime time, String description){
        PatientEntity patientEntity = findOne(patientId);

        if (patientEntity == null) {
            throw new EntityNotFoundException(patientId);
        }

        DoctorEntity doctorEntity = doctorDao.findOne(doctorId);
        if (doctorEntity == null) {
            throw new EntityNotFoundException(doctorId);
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setPatient(patientEntity);
        visitEntity.setDoctor(doctorEntity);
        visitEntity.setTime(time);
        visitEntity.setDescription(description);

        patientEntity.getVisits().add(visitEntity);
        update(patientEntity);
    }

    @Override
    public List<PatientEntity> findPatientsByLastName(String lastName) {
        List <PatientEntity> patients = entityManager
                .createNamedQuery("PatientEntity.findPatientsByLastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getResultList();
        return patients;
    }


    @Override
    public List<PatientEntity> findPatientsThatHaveMoreThanXVisits(Long x) {
        List<PatientEntity> patients = entityManager
                .createNamedQuery("PatientEntity.findPatientsThatHaveMoreThanXVisits", PatientEntity.class)
                .setParameter("x", x)
                .getResultList();
        return patients;
    }

    @Override
    public List<PatientEntity> findPatientsByGender(Gender gender){
        List <PatientEntity> patients = entityManager
                .createNamedQuery("PatientEntity.findPatientsByGender", PatientEntity.class)
                .setParameter("gender", "%" + gender.name() + "%")
                .getResultList();
        return patients;
    }

}

