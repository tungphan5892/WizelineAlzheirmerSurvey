package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.reportfragment;

import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.domain.Repository;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment.BaseFragmentPresenter;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tungphan on 4/17/17.
 */

public class ReportFragmentPresenter extends BaseFragmentPresenter<ReportFragmentView> {

    private static final String TAG = ReportFragmentPresenter.class.getSimpleName();
    private Repository repository;

    @Inject
    public ReportFragmentPresenter(Repository repository) {
        this.repository = repository;
    }

    public void getFirstReportData() {
        subscriptions.add(repository.getReport()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Report>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(Report report) {
                        if (report != null) {
                            getView().onLoadFirstReportSuccess(report);
                        }
                    }
                }));
    }
}
