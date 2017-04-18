package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.surveyfragment.SurveyFragment;


/**
 * Created by tungphan on 4/8/17.
 * Currently I use this activity to create new Survey and also show Survey detail.
 * If the logic get bigger, separate those two feature to independence class
 */

public class SurveyActivity extends SlideMenuActivity implements SurveyView {
    private static final String TAG = SurveyActivity.class.getSimpleName();
    private SurveyFragment surveyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.slide_menu_activity);
        setPresenter(new SurveyPresenter());
        super.onCreate(savedInstanceState);
        getPresenter().onTakeView(this);
        floatingActionButton.setVisibility(View.GONE);
        addSurveyFragment();
        processExtraBundle();
        disableShowNavDrawer();
        enableShowHomeAsUp();
        setBackButtonClickListener();
    }

    private void processExtraBundle() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getBooleanExtra(IntentConstant.SURVEY_VIEW_ONLY, false)) {
                if (intent.getParcelableExtra(IntentConstant.PATIENT_SURVEY_OBJECT) != null) {
                    surveyFragment.setPatientSurvey(intent
                            .getParcelableExtra(IntentConstant.PATIENT_SURVEY_OBJECT));
                }
                surveyFragment.setPatientName(intent
                        .getStringExtra(IntentConstant.PATIENT_NAME));
            } else {
                surveyFragment.setPatientSurveyId(String.valueOf(
                        intent.getIntExtra(IntentConstant.PATIENT_SURVEY_ID, -1)));
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void addSurveyFragment() {
        if (getSupportFragmentManager().findFragmentByTag(SurveyFragment.TAG) == null) {
            if (surveyFragment == null) {
                surveyFragment = new SurveyFragment();
            }
            getSupportFragmentManager().beginTransaction().add(R.id.content_layout
                    , surveyFragment, SurveyFragment.TAG)
                    .commit();
        }
    }
}
