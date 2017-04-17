package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/12/17.
 */

public class Answer implements Parcelable {

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("chose_answer")
    @Expose
    private int[] choseAnswer = null;
    @SerializedName("input_answer")
    @Expose
    private List<String> inputAnswer = null;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int[] getChoseAnswer() {
        return choseAnswer;
    }

    public void setChoseAnswer(int[] choseAnswer) {
        this.choseAnswer = choseAnswer;
    }

    public List<String> getInputAnswer() {
        return inputAnswer;
    }

    public void setInputAnswer(List<String> inputAnswer) {
        this.inputAnswer = inputAnswer;
    }

    public Answer(String questionId, int[] choseAnswer, List<String> inputAnswer) {
        this.questionId = questionId;
        this.choseAnswer = choseAnswer;
        this.inputAnswer = inputAnswer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.questionId);
        dest.writeIntArray(this.choseAnswer);
        dest.writeStringList(this.inputAnswer);
    }

    protected Answer(Parcel in) {
        this.questionId = in.readString();
        this.choseAnswer = in.createIntArray();
        this.inputAnswer = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
