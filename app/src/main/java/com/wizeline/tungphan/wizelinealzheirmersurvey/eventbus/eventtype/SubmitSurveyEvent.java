package com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;

/**
 * Created by tungphan on 4/11/17.
 */

public class SubmitSurveyEvent {

    private PatientSurvey patientSurvey;
    private String surveyId;

    public SubmitSurveyEvent(PatientSurvey patientSurvey, String surveyId) {
        this.patientSurvey = patientSurvey;
        this.surveyId = surveyId;
    }

    public PatientSurvey getPatientSurvey() {
        return patientSurvey;
    }

    public String getSurveyId() {
        return surveyId;
    }
}
