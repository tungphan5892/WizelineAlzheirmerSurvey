package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/11/17.
 */

public class QuestionAndAnswer {

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("question_type")
    @Expose
    private String questionType;
    @SerializedName("question_content")
    @Expose
    private String questionContent;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
