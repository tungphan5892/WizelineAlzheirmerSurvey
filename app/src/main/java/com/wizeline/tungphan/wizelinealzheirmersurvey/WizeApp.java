package com.wizeline.tungphan.wizelinealzheirmersurvey;

import android.app.Application;
import android.content.Context;

import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.AppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.DaggerAppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.AppModule;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.module.EventBusModule;

/**
 * Created by tungphan on 4/8/17.
 */

public class WizeApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    private void initInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .eventBusModule(new EventBusModule())
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        return ((WizeApp)context.getApplicationContext()).appComponent;
    }
}
