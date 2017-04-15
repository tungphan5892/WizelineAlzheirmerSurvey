package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base;

import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.local.LoadLocalData;

import javax.inject.Inject;

/**
 * @author Hien Ngo
 * @since 7/27/16
 */
public class BasePresenter<V extends BaseView> {

    @Inject
    protected LoadLocalData loadLocalData;
    @Inject
    protected RxEventBus rxEventBus;

    private V view;

    public void onTakeView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    public void onDestroyView() {
        this.view = null;
    }

}
