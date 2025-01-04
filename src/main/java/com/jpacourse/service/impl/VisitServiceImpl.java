package com.jpacourse.service.impl;
import com.jpacourse.dto.VisitSimpleTO;
import com.jpacourse.mapper.VisitSimpleMapper;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.service.VisitService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class VisitServiceImpl implements VisitService {

    private final VisitDao visitDao;

    public VisitServiceImpl(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @Override
    public List<VisitSimpleTO> getVisitsByPatientId(Long patientId) {

        List<VisitEntity> visits = visitDao.findByPatientId(patientId);
        return visits.stream()
                .map(VisitSimpleMapper::mapToTO)
                .collect(Collectors.toList());
    }
}
