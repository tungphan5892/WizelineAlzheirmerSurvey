package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Hien Ngo
 * @since 7/27/16
 */
public class BaseFragmentPresenter<V extends BaseFragmentView> {

    protected CompositeSubscription subscriptions;

    public void setSubscriptions(CompositeSubscription subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void unsubscribe() {
        if (subscriptions != null) {
            subscriptions.unsubscribe();
            subscriptions = null;
        }
    }

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
