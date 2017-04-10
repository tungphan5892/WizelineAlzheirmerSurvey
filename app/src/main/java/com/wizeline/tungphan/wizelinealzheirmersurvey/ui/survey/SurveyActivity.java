package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;

import android.os.Bundle;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerSurveyFragment;

/**
 * Created by tungphan on 4/8/17.
 */

public class SurveyActivity extends SlideMenuActivity implements SurveyView {

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
        surveyPresenter.loadSurveyFromLocal();
    }

    @Override
    public void onSaveAssetFileFailed() {
        //show snackbar
    }

    @Override
    public void onLoadLocalSurveySuccess(Survey survey) {
        alzheirmerSurveyFragment.setQuestionAnswerRViewData(survey.getQuestionAnswerList());
    }
}
