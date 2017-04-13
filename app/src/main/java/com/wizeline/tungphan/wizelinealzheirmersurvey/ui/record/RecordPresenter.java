package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record;


import android.content.Context;
import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
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
public class RecordPresenter extends SlideMenuPresenter {

    private static final String TAG = RecordPresenter.class.getSimpleName();
    private final RecordView recordView;

    public RecordPresenter(Context context, RecordView recordView) {
        super(context);
        this.recordView = recordView;
    }

    public void loadReportFromLocal() {
        loadLocalData.loadLocalReport()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Report>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Report report) {
                        recordView.onLoadLocalRecordSuccess(report);
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
                                       recordView.onSaveAssetFileComplete();
                                   } else {
                                       recordView.onSaveAssetFileFailed();
                                   }
                               }
                           }
                );
    }

}
