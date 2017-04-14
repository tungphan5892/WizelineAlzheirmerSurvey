package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuPresenter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey.SurveyActivity;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.PATIENT_SURVEY_ID;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.START_SURVEY_ACTIVITY;

/**
 * @author : hienngo
 * @since : Sep 02, 2016.
 */
public class ReportPresenter extends SlideMenuPresenter {

    private static final String TAG = ReportPresenter.class.getSimpleName();
    private final ReportView reportView;
    private Context context;

    public ReportPresenter(Context context, ReportView reportView) {
        super(context);
        this.context = context;
        this.reportView = reportView;
    }

    public void startNewSurveyActivity(int patientSurveyId) {
        Intent intent = new Intent(context, SurveyActivity.class);
        //temporary generate patient id = patient list size + 1
        intent.putExtra(PATIENT_SURVEY_ID, patientSurveyId + 1);
        ((ReportActivity) context).startActivityForResult(intent, START_SURVEY_ACTIVITY);
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
                        Log.e("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            reportView.onCreateSqliteFromRecordSuccess();
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
                                       reportView.onSaveAssetFileComplete();
                                   } else {
                                       reportView.onSaveAssetFileFailed();
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
                        Log.e("TAG", e.getMessage());
                    }

                    @Override
                    public void onNext(Report report) {
                        if (report != null) {
                            reportView.onLoadReportFromDatabaseSuccess(report);
                        }
                    }
                });
    }

}
