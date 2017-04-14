package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerReportFragment;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.START_SURVEY_ACTIVITY;

/**
 * Created by tungphan on 4/8/17.
 */

public class ReportActivity extends SlideMenuActivity implements ReportView {

    private static final String TAG = ReportActivity.class.getSimpleName();
    private AlzheirmerReportFragment alzheirmerReportFragment;
    private ReportPresenter reportPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(v -> {
            reportPresenter.startNewSurveyActivity(alzheirmerReportFragment
                    .getAlzheirRecordAdapterSize());
        });
        reportPresenter = new ReportPresenter(this, this);
        reportPresenter.saveAssetFiles();
        addAlzheirmerRecordFragment();
        enableShowNavDrawer();
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
    public void onCreateSqliteFromRecordSuccess() {
        reportPresenter.getFirstReportData();
    }

    @Override
    public void onLoadReportFromDatabaseSuccess(Report report) {
        Log.e(TAG, "onLoadLocalSurveySuccess");
        alzheirmerReportFragment.setRecordRecyclerViewData(report);
    }

    @Override
    public void onSaveAssetFileComplete() {
        Log.e(TAG, "onSaveAssetFileComplete");
        reportPresenter.createSqliteFromLocalReport();
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
                reportPresenter.getFirstReportData();
                Snackbar.make(contentLayout, R.string.notify_text_add_patient_survey_success
                        , Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(contentLayout, R.string.notify_text_add_patient_survey_fail
                        , Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
