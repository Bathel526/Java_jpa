package com.jpacourse.service;

import com.jpacourse.dto.VisitSimpleTO;


import java.util.List;

public interface VisitService {
    List<VisitSimpleTO> getVisitsByPatientId (Long patientId);
}
