package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Option;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;

import java.util.List;

/**
 * Created by tungphan on 4/17/17.
 */

public class MixTypeQandA extends LinearQandA
        implements QandAviewHolder {

    public MixTypeQandA(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void setViewData(Answer answer) {
        //For input Q&A, there's one edittext to display datetime and added in parentLayout
        //after questions textview (index: 0).
        //From that, we can easy get the edittext by index: 1.
        //Then set text for it.
        if (answer.getQuestionId().equalsIgnoreCase(questionId)) {
            for (int i = 0; i < options.size(); i++) {
                setViewDataForEachOption(answer, options.get(i), i + 1);
            }
        }
    }

    protected void setViewDataForEachOption(Answer answer, Option option, int index) {
        switch (option.getOptionType()) {
            case ViewConstant.OPTION_RADIO_BUTTON:
                RadioButton radioButton = ((RadioButton) parentLayout.getChildAt(index));
                if (answer.getChoseAnswer().size() > 1
                        && answer.getChoseAnswer().get(0) == index) {
                    radioButton.setChecked(true);
                }
                break;
            case ViewConstant.OPTION_CHECKBOX:
                CheckBox checkBox = ((CheckBox) parentLayout.getChildAt(index));
                if (answer.getChoseAnswer().size() > 2
                        && answer.getChoseAnswer().get(1) == index) {
                    checkBox.setChecked(true);
                }
                break;
            case ViewConstant.OPTION_EDITTEXT:
                EditText editText = ((EditText) parentLayout.getChildAt(index));
                if (answer.getInputAnswer().size() > 1) {
                    editText.setText(answer.getInputAnswer().get(0));
                }
                break;
            case ViewConstant.OPTION_DATETIME:
                editText = ((EditText) parentLayout.getChildAt(index));
                if (answer.getInputAnswer().size() > 2) {
                    editText.setText(answer.getInputAnswer().get(1));
                }
                break;
            default:
                break;
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
