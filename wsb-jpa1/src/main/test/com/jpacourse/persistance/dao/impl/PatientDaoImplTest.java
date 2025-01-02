package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoImplTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    //@Transactional
    @Test
    public void testAddVisitToPatient() {
        // Given:
        PatientEntity patientEntity = patientDao.findOne(1L);

        assertThat(patientEntity).isNotNull();

//        Long patientId = patientEntity.getId();
//        Long doctorId = doctorEntity.getId();
//        String description = "Routine check-up";
//        LocalDateTime visitTime = LocalDateTime.now().plusDays(1);

    }
}
