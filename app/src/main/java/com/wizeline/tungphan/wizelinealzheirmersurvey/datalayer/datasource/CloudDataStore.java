package com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.datasource;

import com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.network.NetworkService;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;

import rx.Observable;

/**
 * {@link DataStore} implementation based on connections to the api (Cloud).
 */
class CloudDataStore implements DataStore {

    private final NetworkService networkService;

    /**
     * Construct a {@link DataStore} based on connections to the api (Cloud).
     *
     * @param networkService The {@link NetworkService} implementation to use.
     */
    CloudDataStore(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Observable<Report> getReport() {
        return this.networkService.getReport();
    }

    @Override
    public Observable<Survey> getSurvey() {
        return this.networkService.getSurvey();
    }

    @Override
    public Observable<Boolean> addSurvey(PatientSurvey patientSurvey, String surveyId) {
        return null;
    }

    @Override
    public Observable<Boolean> createDatabase() {
        return null;
    }
}
