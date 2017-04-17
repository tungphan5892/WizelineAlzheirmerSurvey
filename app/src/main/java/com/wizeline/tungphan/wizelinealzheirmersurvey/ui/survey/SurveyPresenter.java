package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;


import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype.SubmitSurveyEvent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuPresenter;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author : hienngo
 * @since : Sep 02, 2016.
 */
public class SurveyPresenter extends SlideMenuPresenter {

    private static final String TAG = SurveyPresenter.class.getSimpleName();

    public SurveyPresenter() {
    }

}
