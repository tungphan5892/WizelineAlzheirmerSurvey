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
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey.SurveyActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerRecordFragment;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.START_SURVEY_ACTIVITY;

/**
 * Created by tungphan on 4/8/17.
 */

public class RecordActivity extends SlideMenuActivity implements RecordView {
    private static final String TAG = RecordActivity.class.getSimpleName();
    private AlzheirmerRecordFragment alzheirmerRecordFragment;
    private RecordPresenter recordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, SurveyActivity.class);
            this.startActivityForResult(intent, START_SURVEY_ACTIVITY);
        });
        recordPresenter = new RecordPresenter(this, this);
        recordPresenter.saveAssetFiles();
        addAlzheirmerRecordFragment();
    }

    private void addAlzheirmerRecordFragment() {
        if (alzheirmerRecordFragment == null) {
            alzheirmerRecordFragment = new AlzheirmerRecordFragment();
            alzheirmerRecordFragment.initInjector(this);
        }
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout
                , alzheirmerRecordFragment, AlzheirmerRecordFragment.TAG)
                .commit();
    }

    @Override
    public void onLoadLocalRecordSuccess(Report report) {
        Log.e(TAG, "onLoadLocalSurveySuccess");
        alzheirmerRecordFragment.setRecordRecyclerViewData(report);
    }

    @Override
    public void onSaveAssetFileComplete() {
        Log.e(TAG, "onSaveAssetFileComplete");
        recordPresenter.loadReportFromLocal();
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
                recordPresenter.loadReportFromLocal();
                Snackbar.make(contentLayout, R.string.notify_text_add_patient_survey_success
                        , Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(contentLayout, R.string.notify_text_add_patient_survey_fail
                        , Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
