package com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.datasource;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface DataStore {

  Observable<Boolean> createDatabase();

  /**
   * Get an {@link Observable} which will emit a {@link Report}.
   */
  Observable<Report> getReport();

  /**
   * Get an {@link Observable} which will emit a {@link Survey}.
   */
  Observable<Survey> getSurvey();

  Observable<Boolean> addSurvey(PatientSurvey patientSurvey, String surveyId);

}
