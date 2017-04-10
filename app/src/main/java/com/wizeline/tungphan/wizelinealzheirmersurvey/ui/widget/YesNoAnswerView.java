package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;

/**
 * Created by tungphan on 4/10/17.
 */

public class YesNoAnswerView extends RadioGroup implements AnswerView {
    private View view;

    public YesNoAnswerView(Context context) {
        this(context, null);
    }

    public YesNoAnswerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.yes_no_answer, null);
    }

    @Override
    public View createView() {
        return this;
    }
}
