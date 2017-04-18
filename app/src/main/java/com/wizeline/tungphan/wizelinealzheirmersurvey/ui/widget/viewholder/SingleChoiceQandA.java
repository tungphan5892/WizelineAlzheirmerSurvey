package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Option;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;

import java.util.List;

/**
 * Created by tungphan on 4/17/17.
 */

public class SingleChoiceQandA extends LinearQandA
        implements QandAviewHolder {

    private RadioGroup radioGroup;

    public SingleChoiceQandA(Context context, View itemView) {
        super(context, itemView);
        //manage radio buttons inside radio group for single_choice viewholder
        radioGroup = new RadioGroup(context);
        parentLayout.addView(radioGroup);
    }

    @Override
    public RecyclerView.ViewHolder makeViewHolder() {
        return this;
    }

    @Override
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void bindViewHolder(QuestionAndAnswer questionAndAnswer) {
        List<Option> options = questionAndAnswer.getOptions();
        questionId = questionAndAnswer.getQuestionId();
        question.setText(questionAndAnswer.getQuestionContent());
        for (int i = 0; i < options.size(); i++) {
            radioGroup.addView(initView(options.get(i)));
        }
    }

    @Override
    public Answer getAnswer() {
        choseAnswer.add(radioGroup.getCheckedRadioButtonId());
        return new Answer(questionId, choseAnswer, inputAnswer);
    }
}
