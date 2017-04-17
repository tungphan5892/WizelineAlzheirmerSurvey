package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.surveyfragment;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment.BaseFragmentView;

/**
 * Created by tungphan on 4/17/17.
 */

public interface SurveyFragmentView extends BaseFragmentView {
    void onLoadLocalSurveySuccess(Survey survey);

    void onSavePatientSurveySuccess();

    void onSavePatientSurveyFail();

}
