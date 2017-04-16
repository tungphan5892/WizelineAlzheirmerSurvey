package com.wizeline.tungphan.wizelinealzheirmersurvey.di.component;

import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.AppModule;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.EventBusModule;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuActivity;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuPresenter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerReportFragment;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AlzheirmerSurveyFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tung phan on 3/12/17.
 */
@Singleton
@Component(modules = {AppModule.class, EventBusModule.class})
public interface AppComponent {

    void inject(AlzheirmerSurveyFragment alzheirmerSurveyFragment);
    void inject(AlzheirmerReportFragment alzheirmerReportFragment);
    void inject(SlideMenuPresenter slideMenuPresenter);

}
