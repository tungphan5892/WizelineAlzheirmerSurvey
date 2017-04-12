package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tungphan on 4/12/17.
 */

public class Answer {

    public Answer(String questionId, int[] choseAnswer) {
        this.questionId = questionId;
        this.choseAnswer = choseAnswer;
    }

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("chose_answer")
    @Expose
    private int[] choseAnswer;

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
}
