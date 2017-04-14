package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;


import android.content.Context;
import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype.SubmitSurveyEvent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuPresenter;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author : hienngo
 * @since : Sep 02, 2016.
 */
public class SurveyPresenter extends SlideMenuPresenter {

    private static final String TAG = SurveyPresenter.class.getSimpleName();
    private final SurveyView surveyView;

    public SurveyPresenter(Context context, SurveyView surveyView) {
        super(context);
        this.surveyView = surveyView;
        initEventBusObserves();
    }

    private void initEventBusObserves() {
        initSubmitSurveyEventObserve();
    }

    private void initSubmitSurveyEventObserve() {
        rxEventBus.observable(SubmitSurveyEvent.class)
                .subscribe(event ->
                        savePatientSurveyToDatabase(event.getPatientSurvey(), event.getSurveyId()));
    }

    public void savePatientSurveyToDatabase(PatientSurvey patientSurvey, String surveyId) {
        loadLocalData.savePatientSurveyToDatabase(patientSurvey
                , surveyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            surveyView.onSavePatientSurveySuccess();
                        } else {

                        }
                    }

                });
    }

    public void loadSurveyFromLocal() {
        loadLocalData.loadLocalSurvey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Survey>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(Survey survey) {
                        surveyView.onLoadLocalSurveySuccess(survey);
                    }
                });
    }

}
