package com.wizeline.tungphan.wizelinealzheirmersurvey.local;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.AnswerInterface;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.AnswerInterfaceDeserializer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

import rx.Observable;

/**
 * Created by tungphan on 4/10/17.
 */

public class LoadLocalData {
    private final String SURVEY_FILE_NAME = "survey1.json";

    public Observable<Survey> loadLocalSurvey(Context context) {
        return Observable.fromCallable(() -> {
            Gson gson = new GsonBuilder().registerTypeAdapter(AnswerInterface.class,
                    new AnswerInterfaceDeserializer())
                    .create();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(context.getExternalFilesDir(null).getPath() + SURVEY_FILE_NAME));
            } catch (FileNotFoundException | NullPointerException e) {
                e.printStackTrace();
            }
            Type type = new TypeToken<Survey>() {
            }.getType();
            return gson.fromJson(br, type);
        });
    }
}
