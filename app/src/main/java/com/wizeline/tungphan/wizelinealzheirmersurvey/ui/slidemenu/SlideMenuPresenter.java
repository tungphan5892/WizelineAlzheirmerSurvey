package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu;

import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.AppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.domain.Repository;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base.BasePresenter;

import javax.inject.Inject;


/**
 * Created by tungphan on 4/9/17.
 */

public class SlideMenuPresenter extends BasePresenter<SlideMenuView> {

    @Inject
    protected Repository repository;
    @Inject
    protected RxEventBus rxEventBus;

    @Inject
    public SlideMenuPresenter() {
    }

    public void initInjector(AppComponent appComponent) {
        appComponent.inject(this);
    }

}
