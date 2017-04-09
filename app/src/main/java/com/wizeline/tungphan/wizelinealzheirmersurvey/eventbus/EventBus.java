package com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Created by tungphan on 3/22/17.
 */

public interface EventBus {

    void post(@NonNull Object event);

    <T> Observable<T> observable(@NonNull Class<T> eventClass);
}
