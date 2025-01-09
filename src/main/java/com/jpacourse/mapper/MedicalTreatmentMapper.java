package com.jpacourse.mapper;
import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import com.jpacourse.persistence.enums.TreatmentType;

public final class MedicalTreatmentMapper {

    public static MedicalTreatmentTO mapToTO(final MedicalTreatmentEntity medicalTreatmentEntity){
        if (medicalTreatmentEntity == null) {
            return null;
        }
        final MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
        medicalTreatmentTO.setId(medicalTreatmentEntity.getId());
        medicalTreatmentTO.setDescription(medicalTreatmentEntity.getDescription());
        medicalTreatmentTO.setType(medicalTreatmentEntity.getType());
        return medicalTreatmentTO;
    }

    public static MedicalTreatmentEntity mapToEntity(final MedicalTreatmentTO medicalTreatmentTO){
        if (medicalTreatmentTO == null) {
            return null;
        }
        final MedicalTreatmentEntity medicalTreatmentEntity = new MedicalTreatmentEntity();
        medicalTreatmentEntity.setId(medicalTreatmentTO.getId());
        medicalTreatmentEntity.setDescription(medicalTreatmentTO.getDescription());
        medicalTreatmentEntity.setType(medicalTreatmentTO.getType());
        return medicalTreatmentEntity;
    }

    public static TreatmentType mapToTreatmentType(final MedicalTreatmentEntity medicalTreatmentEntity){
        return medicalTreatmentEntity.getType();
    }

    public static MedicalTreatmentTO mapToSimpleTO(final MedicalTreatmentEntity medicalTreatmentEntity){
        if (medicalTreatmentEntity == null) {
            return null;
        }
        final MedicalTreatmentTO medicalTreatmentTO = new MedicalTreatmentTO();
        medicalTreatmentTO.setType(medicalTreatmentEntity.getType());
        return medicalTreatmentTO;
    }
}
