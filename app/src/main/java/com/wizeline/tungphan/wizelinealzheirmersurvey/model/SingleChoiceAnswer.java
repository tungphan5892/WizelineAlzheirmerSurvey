package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tungphan on 4/10/17.
 */

public class SingleChoiceAnswer extends Answer {

    @SerializedName("output_result")
    @Expose
    private String answer;

    public String getOutputResult() {
        return answer;
    }

    public void setOutputResult(String answer) {
        this.answer = answer;
    }
}
