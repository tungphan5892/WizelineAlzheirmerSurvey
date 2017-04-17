package com.wizeline.tungphan.wizelinealzheirmersurvey.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wizeline.tungphan.wizelinealzheirmersurvey.common.Utils;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tungphan on 4/13/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    public static final String DATABASE_NAME = "AlzheirmerSurvey.db";
    private static final int DATABASE_VERSION = 1;
    private static final String REPORT_TABLE_NAME = "report";
    private static final String REPORT_ID = "report_id";
    private static final String SURVEY_ID = "survey_id";
    private static final String SURVEY_TYPE = "survey_type";
    //we still dont need report_id yet so I make it auto increase.
    private static final String CREATE_REPORT_TABLE = "CREATE TABLE " + REPORT_TABLE_NAME
            + "("
            + REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SURVEY_ID + " TEXT,"
            + SURVEY_TYPE + " TEXT"
            + ")";
    private static final String PATIENT_SURVEY_TABLE_NAME = "patient_survey";
    private static final String PATIENT_SURVEY_ID = "patient_survey_id";
    private static final String PATIENT_NAME = "patient_name";
    private static final String CREATE_PATIENT_SURVEY_TABLE = "CREATE TABLE " + PATIENT_SURVEY_TABLE_NAME
            + "("
            + PATIENT_SURVEY_ID + " TEXT PRIMARY KEY,"
            + SURVEY_ID + " TEXT,"
            + PATIENT_NAME + " TEXT,"
            + ")";
    private static final String ANSWER_TABLE_NAME = "answer";
    private static final String ANSWER_ID = "answer_id";
    private static final String QUESTION_ID = "question_id";
    private static final String CHOSE_ANSWER = "chose_answer";
    private static final String CREATE_ANSWER_TABLE = "CREATE TABLE " + ANSWER_TABLE_NAME
            + "("
            + ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PATIENT_SURVEY_ID + " TEXT,"
            + QUESTION_ID + " TEXT,"
            + CHOSE_ANSWER + " TEXT"
            + ")";
    private static final String DROP_REPORT_TABLE = "DROP TABLE IF EXISTS " + REPORT_TABLE_NAME;
    private static final String DROP_PATIENT_SURVEY_TABLE = "DROP TABLE IF EXISTS " + PATIENT_SURVEY_TABLE_NAME;
    private static final String DROP_ANSWER_TABLE = "DROP TABLE IF EXISTS " + ANSWER_TABLE_NAME;
    private static final String SELECT_FIRST_SURVEY_QUERY = "SELECT * FROM "
            + REPORT_TABLE_NAME
            + " where "
            + SURVEY_ID
            + "=";
    private static final String SELECT_PATIENT_SURVEYS = "SELECT * FROM "
            + PATIENT_SURVEY_TABLE_NAME
            + " where "
            + SURVEY_ID
            + "=";
    private static final String SELECT_ANSWERS = "SELECT * FROM "
            + ANSWER_TABLE_NAME
            + " where "
            + PATIENT_SURVEY_ID
            + "=";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private SQLiteDatabase readableSqliteDatabase;
    private SQLiteDatabase writableSqliteDatabase;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REPORT_TABLE);
        db.execSQL(CREATE_PATIENT_SURVEY_TABLE);
        db.execSQL(CREATE_ANSWER_TABLE);
    }

    private void initWritableSqlitedatabase() {
        if (writableSqliteDatabase == null) {
            writableSqliteDatabase = this.getWritableDatabase();
        }
    }

    private void initReadableSqliteDatabase() {
        if (readableSqliteDatabase == null) {
            readableSqliteDatabase = this.getWritableDatabase();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_REPORT_TABLE);
        db.execSQL(DROP_PATIENT_SURVEY_TABLE);
        db.execSQL(DROP_ANSWER_TABLE);
        onCreate(db);
    }

    public void insertAnswer(Answer answer, String patientSurveyId) {
//        Log.e(TAG, "insert answer " + patientSurveyId);
        initWritableSqlitedatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_SURVEY_ID, patientSurveyId);
        contentValues.put(QUESTION_ID, answer.getQuestionId());
        contentValues.put(CHOSE_ANSWER, Arrays.toString(answer.getChoseAnswer()));
        writableSqliteDatabase.insert(ANSWER_TABLE_NAME, null, contentValues);
    }

    public boolean insertPatientSurvey(PatientSurvey patientSurvey, String surveyId) {
        initWritableSqlitedatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PATIENT_SURVEY_ID, patientSurvey.getPatientSurveyId());
        contentValues.put(SURVEY_ID, surveyId);
        contentValues.put(PATIENT_NAME, patientSurvey.getPatientName());
        writableSqliteDatabase.insert(PATIENT_SURVEY_TABLE_NAME, null, contentValues);
        return true;
    }

    public void insertReport(Report report) {
        initWritableSqlitedatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_ID, report.getSurveyId());
        contentValues.put(SURVEY_TYPE, report.getSurveyType());
        writableSqliteDatabase.insert(REPORT_TABLE_NAME, null, contentValues);
    }

    public Report getFirstSurvey() {
        initReadableSqliteDatabase();
        Report report = new Report();
        Cursor res = readableSqliteDatabase.rawQuery(SELECT_FIRST_SURVEY_QUERY + String.valueOf(0), null);
        if (res.moveToFirst()) {
            String surveyId = res.getString(res.getColumnIndex(SURVEY_ID));
            report.setSurveyId(surveyId);
            report.setSurveyType(res.getString(res.getColumnIndex(SURVEY_TYPE)));
            report.setPatientSurveys(getPatientSurveys(surveyId));
        }
        if (!res.isClosed()) {
            res.close();
        }
        return report;
    }

    private List<PatientSurvey> getPatientSurveys(String surveyId) {
        initReadableSqliteDatabase();
        List<PatientSurvey> patientSurveys = new ArrayList<>();
        Cursor res = readableSqliteDatabase.rawQuery(SELECT_PATIENT_SURVEYS + surveyId, null);
        if (res.moveToFirst()) {
            do {
                String patientSurveyId = res.getString(res.getColumnIndex(PATIENT_SURVEY_ID));
                PatientSurvey patientSurvey = new PatientSurvey(patientSurveyId
                        , res.getString(res.getColumnIndex(PATIENT_NAME))
                        , getAnswers(patientSurveyId));
                patientSurveys.add(patientSurvey);
            } while (res.moveToNext());
        }
        if (!res.isClosed()) {
            res.close();
        }
        return patientSurveys;
    }

    private List<Answer> getAnswers(String patientSurveyId) {
        initReadableSqliteDatabase();
        List<Answer> answers = new ArrayList<>();
        Cursor res = readableSqliteDatabase.rawQuery(SELECT_ANSWERS + patientSurveyId, null);
        if (res.moveToFirst()) {
            do {
                String choseAnswer = res.getString(res.getColumnIndex(CHOSE_ANSWER));
                Answer answer = new Answer(
                        res.getString(res.getColumnIndex(QUESTION_ID))
                        , Utils.parseIntArrayFromString(choseAnswer), new ArrayList<>());
                answers.add(answer);
            } while (res.moveToNext());
        }
        if (!res.isClosed()) {
            res.close();
        }
        return answers;
    }
}
