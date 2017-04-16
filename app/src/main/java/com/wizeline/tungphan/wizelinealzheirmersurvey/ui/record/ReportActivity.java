package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey.SurveyActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerReportFragment;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.PATIENT_SURVEY_ID;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.START_SURVEY_ACTIVITY;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.SURVEY_VIEW_ONLY;

/**
 * Created by tungphan on 4/8/17.
 */

public class ReportActivity extends SlideMenuActivity implements ReportView {

    private static final String TAG = ReportActivity.class.getSimpleName();
    private AlzheirmerReportFragment alzheirmerReportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.slide_menu_activity);
        setPresenter(new ReportPresenter());
        super.onCreate(savedInstanceState);
        getPresenter().onTakeView(this);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(v -> startNewSurveyActivity(alzheirmerReportFragment
                .getAlzheirRecordAdapterSize()));
        ((ReportPresenter) getPresenter()).saveAssetFiles();
        addAlzheirmerRecordFragment();
        enableShowNavDrawer();
    }

    private void startNewSurveyActivity(int patientSurveyId) {
        Intent intent = new Intent(this, SurveyActivity.class);
        //temporary generate patient id = patient list size +1
        intent.putExtra(PATIENT_SURVEY_ID, patientSurveyId + 1);
        intent.putExtra(SURVEY_VIEW_ONLY, false);
        startActivityForResult(intent, START_SURVEY_ACTIVITY);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    private void addAlzheirmerRecordFragment() {
        if (alzheirmerReportFragment == null) {
            alzheirmerReportFragment = new AlzheirmerReportFragment();
            alzheirmerReportFragment.initInjector(this);
        }
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout
                , alzheirmerReportFragment, AlzheirmerReportFragment.TAG)
                .commit();
    }

    @Override
    public void onLoadReportFromDatabaseSuccess(Report report) {
        Log.e(TAG, "onLoadReportFromDatabaseSuccess");
        alzheirmerReportFragment.setRecordRecyclerViewData(report);
    }

    @Override
    public void onSaveAssetFileComplete() {
        Log.e(TAG, "onSaveAssetFileComplete");
        ((ReportPresenter) getPresenter()).createSqliteFromLocalReport();
    }

    @Override
    public void onSaveAssetFileFailed() {
        Log.e(TAG, "onSaveAssetFileFailed");
        //show snackbar
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == START_SURVEY_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                ((ReportPresenter) getPresenter()).getFirstReportData();
                Snackbar.make(contentLayout, R.string.notify_text_add_patient_survey_success
                        , Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(contentLayout, R.string.notify_text_add_patient_survey_fail
                        , Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
