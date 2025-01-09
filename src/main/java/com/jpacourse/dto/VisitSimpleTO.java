package com.jpacourse.dto;
import com.jpacourse.persistence.enums.TreatmentType;
import java.time.LocalDateTime;
import java.util.List;

public class VisitSimpleTO
{
    private LocalDateTime time;

    private String DoctorFirstName;

    private String DoctorLastName;

    private List<TreatmentType> treatmentsTypes;

    // Setters and Getters:

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDoctorFirstName() {
        return DoctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        DoctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return DoctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        DoctorLastName = doctorLastName;
    }

    public List<TreatmentType> getTreatmentsTypes() {
        return treatmentsTypes;
    }

    public void setTreatmentsTypes(List<TreatmentType> treatmentsTypes) {
        this.treatmentsTypes = treatmentsTypes;
    }
}
