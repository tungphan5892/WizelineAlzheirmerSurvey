package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Option;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;

import java.util.List;

/**
 * Created by tungphan on 4/17/17.
 */

public class InputAnswerQandA extends LinearQandA
        implements QandAviewHolder {

    public InputAnswerQandA(Context context, View itemView) {
        super(context, itemView);
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
        question.setText(questionAndAnswer.getQuestionContent());
        for (int i = 0; i < options.size(); i++) {
            parentLayout.addView(initView(options.get(i)),i);
        }
    }

    @Override
    public Answer getAnswer() {
        for (int i = 0; i < options.size(); i++) {
            processOutputFromView(options.get(i), i);
        }
        return new Answer(questionId, choseAnswer, inputAnswer);
    }
}
