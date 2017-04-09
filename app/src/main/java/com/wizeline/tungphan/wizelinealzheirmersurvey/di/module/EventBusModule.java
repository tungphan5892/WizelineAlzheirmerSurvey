package com.wizeline.tungphan.wizelinealzheirmersurvey.di.module;


import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tungphan on 3/22/17.
 */
@Module
public class EventBusModule {

    @Provides
    @Singleton
    public RxEventBus getEventBus() {
        return new RxEventBus();
    }

}
