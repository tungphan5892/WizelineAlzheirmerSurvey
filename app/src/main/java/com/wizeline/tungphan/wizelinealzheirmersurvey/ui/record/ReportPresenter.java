package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record;


import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.local.LoadLocalData;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuPresenter;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author : hienngo
 * @since : Sep 02, 2016.
 */
public class ReportPresenter extends SlideMenuPresenter {

    private static final String TAG = ReportPresenter.class.getSimpleName();

    public ReportPresenter() {
    }

    public void createSqliteFromLocalReport() {
        loadLocalData.createSqliteFromLocalReport()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            getFirstReportData();
                        }
                    }
                });
    }

    public void saveAssetFiles() {
        //copy assets files
        Observable.fromCallable(WizeApp.getInstance().copyAssets())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e(TAG, e.getMessage());
                               }

                               @Override
                               public void onNext(Boolean aBoolean) {
                                   if (aBoolean) {
                                       ((ReportView) getView()).onSaveAssetFileComplete();
                                   } else {
                                       ((ReportView) getView()).onSaveAssetFileFailed();
                                   }
                               }
                           }
                );
    }

    public void getFirstReportData() {
        loadLocalData.loadReportFromDatabase()
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
                            ((ReportView) getView()).onLoadReportFromDatabaseSuccess(report);
                        }
                    }
                });
    }

}
