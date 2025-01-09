package com.jpacourse.mapper;
import com.jpacourse.dto.DoctorTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public final class VisitMapper {
    public static VisitTO mapToTO(final VisitEntity visitEntity, PatientTO patientTO, DoctorTO doctorTO) {
        if (visitEntity == null){
            return null;
        }
        if (patientTO == null){
            return null;
        }
        if (doctorTO == null){
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setPatient(patientTO);
        visitTO.setDoctor(doctorTO);
        visitTO.setTreatments(
                visitEntity.getMedicalTreatments()
                    .stream()
                    .map(MedicalTreatmentMapper::mapToTO)
                    .collect(Collectors.toList()));
        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO, PatientEntity patientEntity, DoctorEntity doctorEntity) {
        if (visitTO == null){
            return null;
        }
        if (patientEntity == null){
            return null;
        }
        if (doctorEntity == null){
            return null;
        }
        final VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setDoctor(doctorEntity);
        visitEntity.setPatient(patientEntity);
        visitEntity.setMedicalTreatments(
                visitTO.getTreatments()
                    .stream()
                    .map(MedicalTreatmentMapper::mapToEntity)
                    .collect(Collectors.toList()));
        return visitEntity;
    }
}
