package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuView;

/**
 * Created by tungphan on 4/9/17.
 */

public interface SurveyView extends SlideMenuView {
    void onSaveAssetFileComplete();

    void onSaveAssetFileFailed();

    void onLoadLocalSurveySuccess(Survey survey);
}
