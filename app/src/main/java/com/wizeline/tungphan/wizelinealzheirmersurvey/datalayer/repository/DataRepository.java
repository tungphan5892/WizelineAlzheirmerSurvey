
package com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.repository;


import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.datasource.DataStore;
import com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.datasource.DataStoreFactory;
import com.wizeline.tungphan.wizelinealzheirmersurvey.domain.Repository;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


/**
 * {@link Repository} for retrieving user data.
 */
@Singleton
public class DataRepository implements Repository {

    private final DataStoreFactory dataStoreFactory;

    /**
     * Constructs a {@link Repository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     */
    @Inject
    DataRepository(DataStoreFactory dataStoreFactory) {
        this.dataStoreFactory = dataStoreFactory;
    }

    @Override
    public Observable<Report> getReport() {
        final DataStore dataStore = this.dataStoreFactory.create();
        return dataStore.getReport();
    }

    @Override
    public Observable<Survey> getSurvey() {
        final DataStore dataStore = this.dataStoreFactory.create();
        return dataStore.getSurvey();
    }

    @Override
    public Observable<Boolean> addSurvey(PatientSurvey patientSurvey, String surveyId) {
        final DataStore dataStore = this.dataStoreFactory.create();
        return dataStore.addSurvey(patientSurvey, surveyId);
    }

    @Override
    public Observable<Boolean> createDatabase() {
        final DataStore dataStore = this.dataStoreFactory.create();
        return dataStore.createDatabase();
    }
}
