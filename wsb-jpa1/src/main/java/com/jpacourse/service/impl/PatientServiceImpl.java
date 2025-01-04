package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    private PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public void deletePatientById(Long id){
        PatientEntity patientEntity = patientDao.findOne(id);
        if (patientEntity == null){
            throw new IllegalArgumentException("Patient not found");
        }
        patientDao.delete(id);
    }

    @Override
    public PatientTO findPatientById(Long id){
        PatientEntity patientEntity = patientDao.findOne(id);

        if (patientEntity == null) {
            throw new IllegalArgumentException("Patient not found");
        }
        return PatientMapper.mapToTO(patientEntity);
    }

    @Override
    public List<PatientTO> findAllPatients() {
        List<PatientEntity> patientEntities = patientDao.findAll();
        return patientEntities.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }


    @Override
    public PatientTO addPatient(PatientTO patientTO) {
        PatientEntity patientEntity = PatientMapper.mapToEntity(patientTO);
        patientDao.save(patientEntity);
        return PatientMapper.mapToTO(patientEntity);
    }

}

