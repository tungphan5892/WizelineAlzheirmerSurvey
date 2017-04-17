package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey.SurveyActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.reportfragment.ReportFragment;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.PATIENT_SURVEY_ID;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.START_SURVEY_ACTIVITY;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.SURVEY_VIEW_ONLY;

/**
 * Created by tungphan on 4/8/17.
 */

public class ReportActivity extends SlideMenuActivity implements ReportView {

    private static final String TAG = ReportActivity.class.getSimpleName();
    private ReportFragment reportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.slide_menu_activity);
        setPresenter(new ReportPresenter());
        super.onCreate(savedInstanceState);
        getPresenter().onTakeView(this);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(v -> startNewSurveyActivity(reportFragment
                .getRecordAdapterSize()));
        ((ReportPresenter) getPresenter()).saveAssetFiles();
        addReportFragment();
        enableShowNavDrawer();
    }

    private void startNewSurveyActivity(int patientSurveyId) {
        Intent intent = new Intent(this, SurveyActivity.class);
        //temporary generate patient id = patient list size +1
        intent.putExtra(PATIENT_SURVEY_ID, patientSurveyId + 1);
        intent.putExtra(SURVEY_VIEW_ONLY, false);
        startActivityForResult(intent, START_SURVEY_ACTIVITY);
    }

    private void addReportFragment() {
        if (getSupportFragmentManager().findFragmentByTag(ReportFragment.TAG) == null) {
            if (reportFragment == null) {
                reportFragment = new ReportFragment();
            }
            getSupportFragmentManager().beginTransaction().add(R.id.content_layout
                    , reportFragment, ReportFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onCreateSqliteDatabaseSuccess() {
        if (reportFragment != null) {
            reportFragment.getData();
        }
    }

    @Override
    public void onSaveAssetFileComplete() {
        ((ReportPresenter) getPresenter()).createSqliteFromLocalReport();
    }

    @Override
    public void onSaveAssetFileFailed() {
        //show snackbar
    }
}
