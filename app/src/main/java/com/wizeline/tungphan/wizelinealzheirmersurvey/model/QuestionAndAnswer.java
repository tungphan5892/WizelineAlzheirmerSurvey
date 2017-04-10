package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tungphan on 4/10/17.
 */

public class QuestionAndAnswer {

    @SerializedName("type")
    @Expose
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @SerializedName("output_result")
    @Expose
    private Float outputResult;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private Answer answer;

    public Float getOutputResult() {
        return outputResult;
    }

    public void setOutputResult(Float outputResult) {
        this.outputResult = outputResult;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
