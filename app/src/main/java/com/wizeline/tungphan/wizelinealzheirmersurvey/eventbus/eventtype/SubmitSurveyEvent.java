package com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;

/**
 * Created by tungphan on 4/11/17.
 */

public class SubmitSurveyEvent {

    private PatientSurvey patientSurvey;

    public SubmitSurveyEvent(PatientSurvey patientSurvey){
        this.patientSurvey = patientSurvey;
    }

    public PatientSurvey getPatientSurvey(){
        return patientSurvey;
    }
}
