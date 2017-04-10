package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tungphan on 4/10/17.
 */

public class Survey {

    @SerializedName("survey_type")
    @Expose
    private String surveyType;
    @SerializedName("question_answer_list")
    @Expose
    private List<QuestionAndAnswer> questionAnswers = null;

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public List<QuestionAndAnswer> getQuestionAnswerList() {
        return questionAnswers;
    }

    public void setQuestionAnswerList(List<QuestionAndAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

}
