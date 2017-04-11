package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/11/17.
 */

public class PatientSurvey {
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("question_answer_list")
    @Expose
    private List<QuestionAndAnswer> questionAndAnswers = null;
    @SerializedName("disease_cause_percentage")
    @Expose
    private Float diseaseCausePercentage;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<QuestionAndAnswer> getQuestionAndAnswers() {
        return questionAndAnswers;
    }

    public void setQuestionAndAnswers(List<QuestionAndAnswer> questionAndAnswers) {
        this.questionAndAnswers = questionAndAnswers;
    }

    public Float getDiseaseCausePercentage() {
        return diseaseCausePercentage;
    }

    public void setDiseaseCausePercentage(Float diseaseCausePercentage) {
        this.diseaseCausePercentage = diseaseCausePercentage;
    }

}
