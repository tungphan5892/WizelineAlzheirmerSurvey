package com.wizeline.tungphan.wizelinealzheirmersurvey.di.component;

import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.AppModule;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.EventBusModule;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base.BasePresenter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerRecordFragment;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerSurveyFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tung phan on 3/12/17.
 */
@Singleton
@Component(modules = {AppModule.class, EventBusModule.class})
public interface AppComponent {

    void inject(BasePresenter basePresenter);
    void inject(AlzheirmerSurveyFragment alzheirmerSurveyFragment);
    void inject(AlzheirmerRecordFragment alzheirmerRecordFragment);

}
