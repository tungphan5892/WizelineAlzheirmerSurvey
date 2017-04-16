package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Hien Ngo
 * @since 7/27/16
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseView {
    private Unbinder unbinder;

    protected CompositeSubscription subscriptions;
    @Inject
    P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onTakeView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        getPresenter().onDestroyView();
        if (subscriptions != null) {
            subscriptions.unsubscribe();
            subscriptions = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void setPresenter(P presenter){
        this.presenter = presenter;
    }

    @Override
    final public P getPresenter() {
        return presenter;
    }

}
