package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.surveyfragment;

import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.local.LoadLocalData;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment.BaseFragmentPresenter;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tungphan on 4/17/17.
 */

public class SurveyFragmentPresenter extends BaseFragmentPresenter<SurveyFragmentView> {

    private static final String TAG = SurveyFragmentPresenter.class.getSimpleName();
    private LoadLocalData loadLocalData;

    @Inject
    public SurveyFragmentPresenter(LoadLocalData loadLocalData) {
        this.loadLocalData = loadLocalData;
    }

    public void loadSurveyFromLocal() {
        subscriptions.add(loadLocalData.loadLocalSurvey()
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
                        getView().onLoadLocalSurveySuccess(survey);
                    }
                }));
    }

    public void savePatientSurveyToDatabase(PatientSurvey patientSurvey, String surveyId) {
        subscriptions.add(loadLocalData.savePatientSurveyToDatabase(patientSurvey
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
                            getView().onSavePatientSurveySuccess();
                        } else {
                            getView().onSavePatientSurveyFail();
                        }
                    }

                })
        );
    }
}
