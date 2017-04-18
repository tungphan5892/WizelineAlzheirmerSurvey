package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

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
    public void setViewData(Answer answer) {
        //For input Q&A, there's one edittext to display datetime and added in parentLayout
        //after questions textview (index: 0).
        //From that, we can easy get the edittext by index: 1.
        //Then set text for it.
        if (answer.getQuestionId().equalsIgnoreCase(questionId)
                && answer.getInputAnswer().size() > 0) {
            ((EditText) parentLayout.getChildAt(1)).setText(answer.getInputAnswer().get(0));
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
