package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;


import android.content.Context;
import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuPresenter;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author : hienngo
 * @since : Sep 02, 2016.
 */
public class SurveyPresenter extends SlideMenuPresenter {

    private final SurveyView surveyView;
    private Context context;

    public SurveyPresenter(Context context, SurveyView surveyView) {
        super(context);
        this.context = context;
        this.surveyView = surveyView;
    }

    public void saveAssetFiles() {
        //copy assets files
        Observable.fromCallable(WizeApp.getInstance().copyAssets())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("TAG", e.getMessage());
                               }

                               @Override
                               public void onNext(Boolean aBoolean) {
                                   if (aBoolean) {
                                       surveyView.onSaveAssetFileComplete();
                                   } else {
                                       surveyView.onSaveAssetFileFailed();
                                   }
                               }
                           }
                );
    }

    public void loadSurveyFromLocal() {
        loadLocalData.loadLocalSurvey(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Survey>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Survey survey) {
                        surveyView.onLoadLocalSurveySuccess(survey);
                    }
                });
    }

}
