package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;


/**
 * Created by tungphan on 4/17/17.
 */

public class MultiChoicesQandA extends LinearQandA
        implements QandAviewHolder {

    public MultiChoicesQandA(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void setViewData(Answer answer) {
        for (int i = 0; i < answer.getChoseAnswer().size(); i++) {
            //get number of the checked box from chosed answer list
            final int checkedAnswer = answer.getChoseAnswer().get(i) + 1;
            ((CheckBox) parentLayout.getChildAt(checkedAnswer)).setChecked(true);
        }
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
        options = questionAndAnswer.getOptions();
        question.setText(questionAndAnswer.getQuestionContent());
        questionId = questionAndAnswer.getQuestionId();
        for (int i = 0; i < options.size(); i++) {
            parentLayout.addView(initView(options.get(i)));
        }
    }

    @Override
    public Answer getAnswer() {
        //there's a textview at position 0, get the other from i+1
        for (int i = 0; i < options.size(); i++) {
            processOutputFromView(options.get(i), i + 1);
        }
        return new Answer(questionId, choseAnswer, inputAnswer);
    }
}
