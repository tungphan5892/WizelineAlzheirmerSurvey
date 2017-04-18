package com.wizeline.tungphan.wizelinealzheirmersurvey.datalayer.network;


import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by tung phan on 04-18-2017.
 * implement call to the interface here.
 */
public interface NetworkService {

    @GET("/report_url")
    Observable<Report> getReport();

    @GET("/survey_url")
    Observable<Survey> getSurvey();
}
