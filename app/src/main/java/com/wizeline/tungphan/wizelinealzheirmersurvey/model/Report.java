package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/11/17.
 */

public class Report {
    @SerializedName("survey_type")
    @Expose
    private String surveyType;
    @SerializedName("patient_survey_list")
    @Expose
    private List<PatientSurvey> patientSurveys = null;

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public List<PatientSurvey> getPatientSurveys() {
        return patientSurveys;
    }

    public void setPatientSurveys(List<PatientSurvey> patientSurveys) {
        this.patientSurveys = patientSurveys;
    }
}
