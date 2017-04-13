package com.wizeline.tungphan.wizelinealzheirmersurvey.local;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import rx.Observable;
import rx.functions.Func1;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.FileConstant.REPORT_FILE_NAME;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.FileConstant.SURVEY_FILE_NAME;

/**
 * Created by tungphan on 4/10/17.
 */

public class LoadLocalData {
    private static final String TAG = LoadLocalData.class.getSimpleName();

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

    public Observable<Boolean> savePatientSurveyToLocal(PatientSurvey patientSurvey) {
        return loadLocalReport().flatMap(new Func1<Report, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call(Report report) {
                return addPatientSurveyToFile(report, patientSurvey);
            }
        });
    }

    private Observable<Boolean> addPatientSurveyToFile(Report report, PatientSurvey patientSurvey) {
        return Observable.create(subscriber -> {
            int addIndex = report.getPatientSurveys().size();
            report.getPatientSurveys().add(addIndex, patientSurvey);
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(
                        WizeApp.getInstance().getExternalFilesDir(null) + REPORT_FILE_NAME);
                Gson gson = new Gson();
                String data = gson.toJson(report);
                fileWriter.write(data);
                subscriber.onNext(true);
            } catch (Exception e) {
                subscriber.onNext(false);
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            subscriber.onCompleted();
        });

    }
}
