package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base;

import android.content.Context;

import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.local.LoadLocalData;

import javax.inject.Inject;

/**
 * @author Hien Ngo
 * @since 7/27/16
 */
public class BasePresenter {


    @Inject
    protected LoadLocalData loadLocalData;
    private Context context;

    public BasePresenter() {

    }

    public BasePresenter(Context context) {
        this.context = context;
        initInjector();
    }

    public void initInjector() {
        WizeApp.getAppComponent(context).inject(this);
    }

}
