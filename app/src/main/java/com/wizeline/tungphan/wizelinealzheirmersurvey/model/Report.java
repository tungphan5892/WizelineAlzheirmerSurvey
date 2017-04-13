package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/11/17.
 */

public class Report {

    public Report() {

    }

    public Report(String surveyId, String surveyType, List<PatientSurvey> patientSurveys) {
        this.surveyId = surveyId;
        this.surveyType = surveyType;
        this.patientSurveys = patientSurveys;
    }

    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("survey_type")
    @Expose
    private String surveyType;
    @SerializedName("patient_survey_list")
    @Expose
    private List<PatientSurvey> patientSurveys = null;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

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
