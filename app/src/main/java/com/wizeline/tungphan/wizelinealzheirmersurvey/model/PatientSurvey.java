package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/11/17.
 */

public class PatientSurvey {

    public PatientSurvey(String patientName, List<Answer> answers
            , Float diseaseCausePercentage) {
        this.patientName = patientName;
        this.answers = answers;
        this.diseaseCausePercentage = diseaseCausePercentage;
    }

    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("answer_list")
    @Expose
    private List<Answer> answers = null;
    @SerializedName("disease_cause_percentage")
    @Expose
    private Float diseaseCausePercentage;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Float getDiseaseCausePercentage() {
        return diseaseCausePercentage;
    }

    public void setDiseaseCausePercentage(Float diseaseCausePercentage) {
        this.diseaseCausePercentage = diseaseCausePercentage;
    }

}
