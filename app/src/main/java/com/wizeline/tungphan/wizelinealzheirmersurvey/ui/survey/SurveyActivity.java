package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;

import android.os.Bundle;
import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerSurveyFragment;

/**
 * Created by tungphan on 4/8/17.
 */

public class SurveyActivity extends SlideMenuActivity implements SurveyView {
    private static final String TAG = SurveyActivity.class.getSimpleName();
    private AlzheirmerSurveyFragment alzheirmerSurveyFragment;
    private SurveyPresenter surveyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surveyPresenter = new SurveyPresenter(this, this);
        surveyPresenter.saveAssetFiles();
        addAlzheirmerSurveyFragment();
    }

    private void addAlzheirmerSurveyFragment() {
        if (alzheirmerSurveyFragment == null) {
            alzheirmerSurveyFragment = new AlzheirmerSurveyFragment();
        }
        getSupportFragmentManager().beginTransaction().add(R.id.content_layout
                , alzheirmerSurveyFragment, AlzheirmerSurveyFragment.TAG)
                .commit();
    }

    @Override
    public void onSaveAssetFileComplete() {
        Log.e(TAG,"onSaveAssetFileComplete");
        surveyPresenter.loadSurveyFromLocal();
    }

    @Override
    public void onSaveAssetFileFailed() {
        Log.e(TAG,"onSaveAssetFileFailed");
        //show snackbar
    }

    @Override
    public void onLoadLocalSurveySuccess(Survey survey) {
        Log.e(TAG,"onLoadLocalSurveySuccess");
        alzheirmerSurveyFragment.setQuestionAnswerRViewData(survey.getQuestionAndAnswers());
    }
}
