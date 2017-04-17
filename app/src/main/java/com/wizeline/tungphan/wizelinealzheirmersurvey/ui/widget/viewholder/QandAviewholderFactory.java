package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.content.Context;
import android.view.View;

import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;

/**
 * Created by tungphan on 4/17/17.
 */

public class QandAviewholderFactory {

    public QandAviewHolder getQandAviewHolder(Context context, String qAndAtype, View view) {
        if (qAndAtype == null) {
            return null;
        }
        if (qAndAtype.equalsIgnoreCase(ViewConstant.SINGLE_CHOICE)) {
            return new SingleChoiceQandA(context, view);
        } else if (qAndAtype.equalsIgnoreCase(ViewConstant.MULTI_CHOICES)) {
            return new MultiChoicesQandA(context, view);

        } else if (qAndAtype.equalsIgnoreCase(ViewConstant.INPUT_ANSWER)) {
            return new InputAnswerQandA(context, view);
        } else if (qAndAtype.equalsIgnoreCase(ViewConstant.DATETIME)) {
            return new DateTimeQandA(context, view);
        } else if (qAndAtype.equalsIgnoreCase(ViewConstant.MIX_TYPE)) {
            return new MixTypeQandA(context, view);
        }
        return null;
    }

}
