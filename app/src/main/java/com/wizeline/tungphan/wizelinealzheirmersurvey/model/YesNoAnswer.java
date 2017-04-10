package com.wizeline.tungphan.wizelinealzheirmersurvey.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tungphan on 4/10/17.
 */

public class YesNoAnswer extends Answer {

    @SerializedName("answer")
    @Expose
    private Boolean answer;

    public Boolean getOutputResult() {
        return answer;
    }

    public void setOutputResult(Boolean answer) {
        this.answer = answer;
    }
}
