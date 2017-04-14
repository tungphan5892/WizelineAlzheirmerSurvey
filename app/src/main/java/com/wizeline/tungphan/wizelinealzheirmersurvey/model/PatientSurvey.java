package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/11/17.
 */

public class PatientSurvey implements Parcelable {

    @SerializedName("patient_survey_id")
    @Expose
    private String patientSurveyId;
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("answer_list")
    @Expose
    private List<Answer> answers = null;
    @SerializedName("disease_cause_percentage")
    @Expose
    private Float diseaseCausePercentage;

    public PatientSurvey(String patientSurveyId, String patientName, List<Answer> answers
            , Float diseaseCausePercentage) {
        this.patientSurveyId = patientSurveyId;
        this.patientName = patientName;
        this.answers = answers;
        this.diseaseCausePercentage = diseaseCausePercentage;
    }

    public String getPatientSurveyId() {
        return patientSurveyId;
    }

    public void setPatientSurveyId(String patientSurveyId) {
        this.patientSurveyId = patientSurveyId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Float getDiseaseCausePercentage() {
        return diseaseCausePercentage;
    }

    public void setDiseaseCausePercentage(Float diseaseCausePercentage) {
        this.diseaseCausePercentage = diseaseCausePercentage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.patientSurveyId);
        dest.writeString(this.patientName);
        dest.writeTypedList(this.answers);
        dest.writeValue(this.diseaseCausePercentage);
    }

    protected PatientSurvey(Parcel in) {
        this.patientSurveyId = in.readString();
        this.patientName = in.readString();
        this.answers = in.createTypedArrayList(Answer.CREATOR);
        this.diseaseCausePercentage = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Parcelable.Creator<PatientSurvey> CREATOR = new Parcelable.Creator<PatientSurvey>() {
        @Override
        public PatientSurvey createFromParcel(Parcel source) {
            return new PatientSurvey(source);
        }

        @Override
        public PatientSurvey[] newArray(int size) {
            return new PatientSurvey[size];
        }
    };
}
