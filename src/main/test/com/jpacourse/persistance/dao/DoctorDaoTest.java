package com.jpacourse.persistance.dao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThrows;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorDaoTest {

    @Autowired
    private DoctorDao doctorDao;



    @Test
    public void testShouldFindVersionOdDoctorAfterModify() {
//        Given:
        DoctorEntity doctor = doctorDao.findOne(1L);
//        When:
        doctor.setFirstName("Vernom");
        doctor.setLastName("Roche");
        doctor.setTelephoneNumber("997-997-997");
        doctor.setEmail("alla@wp.pl");
        doctorDao.update(doctor);

        DoctorEntity doctorV = doctorDao.findOne(1L);

//        Then:
        assertThat(doctorV.getVersion()).isEqualTo(1);
    }


    @Test
    public void testShouldFindVersionOdDoctorAfterTwoUpdate(){
//        Given:
        DoctorEntity doctor2 = doctorDao.findOne(2L);
//        When:
        System.out.println("Początkowa wersja: " + doctor2.getVersion());
        doctor2.setFirstName("Arnold");
        doctorDao.update(doctor2);

        DoctorEntity doctor22 = doctorDao.findOne(2L);
        doctor22.setLastName("Boczek");
        doctorDao.update(doctor22);

        DoctorEntity doctor2V = doctorDao.findOne(2L);
        System.out.println("Wersja po zapisie: " + doctor2V.getVersion());
//        Then:
        assertThat(doctor2V.getVersion()).isEqualTo(2);
    }

    @Test
    public void testOptimisticLocking() {
//        Given:
        DoctorEntity doctor1 = doctorDao.findOne(1L);
        DoctorEntity doctor2 = doctorDao.findOne(1L);
//        Then:
        doctor1.setFirstName("Marian");
        doctorDao.update(doctor1);
//      parallel modification
        doctor2.setFirstName("Doe");
//      Then:
        assertThrows(OptimisticLockingFailureException.class, () -> {
            doctorDao.update(doctor2);
        });
    }


}
