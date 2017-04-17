package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Hien Ngo
 * @since 7/27/16
 */
public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment
        implements BaseFragmentView {
    protected Unbinder unbinder;
    @Inject
    P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initInjector(WizeApp.getAppComponent(getContext()));
        super.onCreate(savedInstanceState);
        getPresenter().onTakeView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().setSubscriptions(new CompositeSubscription());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        getPresenter().onDestroyView();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().unsubscribe();
    }

    @Override
    final public P getPresenter() {
        return presenter;
    }

    protected abstract void initInjector(AppComponent appComponent);

}
