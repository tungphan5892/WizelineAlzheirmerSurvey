package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base.BasePresenter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuPresenter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerSurveyFragment;


/**
 * Created by tungphan on 4/8/17.
 * Currently I use this activity to create new Survey and also show Survey detail.
 * If the logic get bigger, separate those two feature to independence class
 */

public class SurveyActivity extends SlideMenuActivity implements SurveyView {
    private static final String TAG = SurveyActivity.class.getSimpleName();
    private AlzheirmerSurveyFragment alzheirmerSurveyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.slide_menu_activity);
        super.onCreate(savedInstanceState);
        setPresenter(new SlideMenuPresenter());
        getPresenter().onTakeView(this);
        floatingActionButton.setVisibility(View.GONE);
        ((SurveyPresenter) getPresenter()).loadSurveyFromLocal();
        addAlzheirmerSurveyFragment();
        processExtraBundle();
        disableShowNavDrawer();
        enableShowHomeAsUp();
        setBackButtonClickListener();
    }

    private void processExtraBundle() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getBooleanExtra(IntentConstant.SURVEY_VIEW_ONLY, false)) {
                alzheirmerSurveyFragment.setEditable(false);
                if (intent.getParcelableExtra(IntentConstant.PATIENT_SURVEY_OBJECT) != null) {
                    alzheirmerSurveyFragment.setPatientSurvey(intent
                            .getParcelableExtra(IntentConstant.PATIENT_SURVEY_OBJECT));
                }
                alzheirmerSurveyFragment.setPatientName(intent
                        .getStringExtra(IntentConstant.PATIENT_NAME));
            } else {
                alzheirmerSurveyFragment.setEditable(true);
                alzheirmerSurveyFragment.setPatientSurveyId(String.valueOf(
                        intent.getIntExtra(IntentConstant.PATIENT_SURVEY_ID, -1)));
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((SurveyPresenter) getPresenter()).setSubscriptions(subscriptions);
    }

    private void addAlzheirmerSurveyFragment() {
        if (alzheirmerSurveyFragment == null) {
            alzheirmerSurveyFragment = new AlzheirmerSurveyFragment();
            alzheirmerSurveyFragment.initInjector(this);
        }
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout
                , alzheirmerSurveyFragment, AlzheirmerSurveyFragment.TAG)
                .commit();
    }

    @Override
    public void onLoadLocalSurveySuccess(Survey survey) {
        Log.e(TAG, "onLoadLocalSurveySuccess");
        alzheirmerSurveyFragment.setQuestionAnswerRViewData(survey);
    }

    @Override
    public void onSavePatientSurveySuccess() {
        this.setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void onSavePatientSurveyFail() {
        this.setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
