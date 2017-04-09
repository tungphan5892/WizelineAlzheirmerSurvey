package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;

import android.os.Bundle;

import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.AppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.view.MainActivity;

/**
 * Created by tungphan on 4/8/17.
 */

public class SurveyActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void initInjector(AppComponent appComponent){
        appComponent.inject(this);
    }
}
