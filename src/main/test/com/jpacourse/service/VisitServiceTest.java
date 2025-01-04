package com.jpacourse.service;
import com.jpacourse.dto.VisitSimpleTO;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.enums.Gender;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VisitServiceTest {



    @Autowired
    private VisitService visitService;


    @Transactional
    @Test
    public void testShouldFindVisitsByPatientId(){

        Long patientId = 1L;
        List<VisitSimpleTO> VisitSimpleTOs = visitService.getVisitsByPatientId(patientId);

        Assertions.assertThat(VisitSimpleTOs).isNotEmpty();
    }

    @Transactional
    @Test
    public void testShouldNotFindVisitsByPatientId(){

        Long patientId = 999L;
        List<VisitSimpleTO> VisitSimpleTOs = visitService.getVisitsByPatientId(patientId);

        Assertions.assertThat(VisitSimpleTOs).isEmpty();

    }

}
