package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget;

import android.content.Context;

import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;

/**
 * Created by tungphan on 4/10/17.
 */

public class AnswerViewFactory {

    public AnswerView getAnswerView(Context context, String answerType) {
        if (answerType == null) {
            return null;
        }
        if (answerType.equalsIgnoreCase(ViewConstant.TYPE_YES_NO)) {
            return new YesNoAnswerView(context);

        } else if (answerType.equalsIgnoreCase(ViewConstant.TYPE_SINGLE_CHOICE)) {
            return new SingleChoiceAnswerView(context);
        }
        return null;
    }
}
