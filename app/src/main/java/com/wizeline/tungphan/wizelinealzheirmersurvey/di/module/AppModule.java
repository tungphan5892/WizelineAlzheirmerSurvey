package com.wizeline.tungphan.wizelinealzheirmersurvey.di.module;

import android.app.Application;
import android.content.Context;

import com.wizeline.tungphan.wizelinealzheirmersurvey.local.DatabaseHelper;
import com.wizeline.tungphan.wizelinealzheirmersurvey.local.LoadLocalData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tungphan on 4/9/17.
 */
@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.application;
    }

    @Provides
    @Singleton
    LoadLocalData provideLoadLocalData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(application);
        return new LoadLocalData(databaseHelper);
    }
}
