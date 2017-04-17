package com.wizeline.tungphan.wizelinealzheirmersurvey.local;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.FileConstant.REPORT_FILE_NAME;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.FileConstant.SURVEY_FILE_NAME;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.local.DatabaseHelper.DATABASE_NAME;

/**
 * Created by tungphan on 4/10/17.
 */

public class LoadLocalData {
    private static final String TAG = LoadLocalData.class.getSimpleName();
    private DatabaseHelper databaseHelper;

    public LoadLocalData(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public Observable<Survey> loadLocalSurvey() {
        return Observable.fromCallable(() -> {
            Gson gson = new Gson();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(
                        WizeApp.getInstance().getExternalFilesDir(null)
                                + SURVEY_FILE_NAME));
            } catch (FileNotFoundException | NullPointerException e) {
                e.printStackTrace();
            }
            Type type = new TypeToken<Survey>() {
            }.getType();
            return gson.fromJson(br, type);
        });
    }

    //get the report from database by loading the first survey.
    public Observable<Report> loadReportFromDatabase() {
        return Observable.fromCallable(() -> databaseHelper.getFirstSurvey());
    }

    public Observable<Report> loadLocalReport() {
        return Observable.fromCallable(() -> {
            Gson gson = new Gson();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(
                        WizeApp.getInstance().getExternalFilesDir(null)
                                + REPORT_FILE_NAME));
            } catch (FileNotFoundException | NullPointerException e) {
                e.printStackTrace();
            }
            Type type = new TypeToken<Report>() {
            }.getType();
            return gson.fromJson(br, type);
        });
    }

    public Observable<Boolean> createSqliteFromLocalReport() {
        return loadLocalReport().flatMap(new Func1<Report, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(Report report) {
                return createReportSqlite(report);
            }
        });
    }

    private Observable<Boolean> createReportSqlite(Report report) {
        return Observable.create(subscriber -> {
            //if database exist. skip it
            File databaseFile = new File(WizeApp.getInstance().getDatabasePath(DATABASE_NAME), "");
            if (!databaseFile.exists()) {
                databaseHelper.insertReport(report);
                List<PatientSurvey> patientSurveys = report.getPatientSurveys();
                for (int i = 0; i < patientSurveys.size(); i++) {
                    final PatientSurvey patientSurvey = patientSurveys.get(i);
                    databaseHelper.insertPatientSurvey(patientSurvey
                            , report.getSurveyId());
                    final List<Answer> answers = patientSurvey.getAnswers();
                    for (int j = 0; j < answers.size(); j++) {
                        databaseHelper.insertAnswer(answers.get(j), patientSurvey.getPatientSurveyId());
                    }
                }
            }
            subscriber.onNext(true);
            subscriber.onCompleted();
        });
    }

    public Observable<Boolean> savePatientSurveyToDatabase(PatientSurvey patientSurvey, String surveyId) {
        return Observable.fromCallable(() -> {
                    databaseHelper.insertPatientSurvey(patientSurvey, surveyId);
                    List<Answer> answers = patientSurvey.getAnswers();
                    for (int i = 0; i < answers.size(); i++) {
                        databaseHelper.insertAnswer(answers.get(i), patientSurvey.getPatientSurveyId());
                    }
                    return true;
                }
        );
    }
}
