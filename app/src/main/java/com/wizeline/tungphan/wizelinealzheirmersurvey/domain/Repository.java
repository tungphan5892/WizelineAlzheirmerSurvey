package com.wizeline.tungphan.wizelinealzheirmersurvey.domain;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Report} related data.
 */
public interface Repository {

    Observable<Boolean> createDatabase();

    /**
     * Get an {@link Observable} which will emit a List of {@link Report}.
     */
    Observable<Report> getReport();

    /**
     * Get an {@link Observable} which will emit a List of {@link Report}.
     */
    Observable<Survey> getSurvey();

    Observable<Boolean> addSurvey(PatientSurvey patientSurvey, String surveyId);
}
