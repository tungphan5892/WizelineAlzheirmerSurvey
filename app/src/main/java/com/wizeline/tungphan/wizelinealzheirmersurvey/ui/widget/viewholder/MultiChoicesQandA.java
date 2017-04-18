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

public class MultiChoicesQandA extends LinearQandA
        implements QandAviewHolder {

    public MultiChoicesQandA(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public RecyclerView.ViewHolder makeViewHolder() {
        return this;
    }

    @Override
    public void setEditable(boolean editable) {

    }

    public void bindViewHolder(QuestionAndAnswer questionAndAnswer) {
        List<Option> options = questionAndAnswer.getOptions();
        question.setText(questionAndAnswer.getQuestionContent());
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
