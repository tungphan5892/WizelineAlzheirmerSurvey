package com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype;


/**
 * Created by tungphan on 4/13/17.
 */

public class IllegalInputEvent {

    public enum InputType {
        NAME_EDITTEXT,
        NOT_INTERACTED,
        LEGAL
    }

    private InputType inputType;
    //using submitSurveyEvent to save patient survey in necessary case.
    private SubmitSurveyEvent submitSurveyEvent;

    public IllegalInputEvent(InputType inputType, SubmitSurveyEvent submitSurveyEvent) {
        this.inputType = inputType;
        this.submitSurveyEvent = submitSurveyEvent;
    }

    public InputType getInputType() {
        return inputType;
    }

    public SubmitSurveyEvent getSubmitSurveyEvent() {
        return submitSurveyEvent;
    }

}
