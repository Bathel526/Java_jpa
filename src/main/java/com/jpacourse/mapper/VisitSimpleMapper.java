package com.jpacourse.mapper;
import com.jpacourse.dto.VisitSimpleTO;
import com.jpacourse.persistence.entity.VisitEntity;
import java.util.stream.Collectors;
import com.jpacourse.dto.MedicalTreatmentTO;

public final class VisitSimpleMapper {

    public static VisitSimpleTO mapToTO(final VisitEntity visitEntity) {
        if (visitEntity == null){
            return null;
        }
        final VisitSimpleTO visitSimpleTO = new VisitSimpleTO();
        visitSimpleTO.setTime(visitEntity.getTime());
        visitSimpleTO.setDoctorFirstName(visitEntity.getDoctor().getFirstName());
        visitSimpleTO.setDoctorLastName(visitEntity.getDoctor().getLastName());
        visitSimpleTO.setTreatmentsTypes(
                visitEntity.getMedicalTreatments()
                        .stream()
//                        .map(MedicalTreatmentMapper::mapToSimpleTO)
//                        .map(MedicalTreatmentTO::getType)
                        .map(MedicalTreatmentMapper::mapToTreatmentType)
                        .collect(Collectors.toList())
        );
        return visitSimpleTO;
    }

}
