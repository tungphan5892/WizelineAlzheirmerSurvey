package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/10/17.
 */

public class QuestionAndAnswerAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QuestionAndAnswer> questionAndAnswerList;
    private Context context;

    public QuestionAndAnswerAdapter(Context context, List<QuestionAndAnswer> questionAndAnswerList) {
        this.context = context;
        this.questionAndAnswerList = questionAndAnswerList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QuestionAndAnswer questionAndAnswer = questionAndAnswerList.get(viewType);
        switch (questionAndAnswer.getQuestionType()) {
//            case ViewConstant.TYPE_YES_NO:
//                View itemView = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.yes_no_question_answer, parent, false);
//                return new YesNoQuestionAnswerViewHolder(itemView);
            case ViewConstant.TYPE_GENDER:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.single_choice_question_answer, parent, false);
                return new SingleChoiceQuestionAnswerViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.yes_no_question_answer, parent, false);
                return new YesNoQuestionAnswerViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        QuestionAndAnswer questionAndAnswer = questionAndAnswerList.get(position);
        switch (questionAndAnswer.getQuestionType()) {
//            case ViewConstant.TYPE_YES_NO:
//                YesNoQuestionAnswerViewHolder yesNoQuestionAnswerViewHolder
//                        = (YesNoQuestionAnswerViewHolder) holder;
//                yesNoQuestionAnswerViewHolder.question.setText(questionAndAnswer.getQuestionContent());
//                break;

            case ViewConstant.TYPE_GENDER:
                SingleChoiceQuestionAnswerViewHolder singleChoiceQuestionAnswerViewHolder
                        = (SingleChoiceQuestionAnswerViewHolder) holder;
                singleChoiceQuestionAnswerViewHolder.question.setText(questionAndAnswer.getQuestionContent());
                singleChoiceQuestionAnswerViewHolder.firstRadioBtn
                        .setText(questionAndAnswer.getOptions().get(0).getOption());
                singleChoiceQuestionAnswerViewHolder.secondRadioBtn
                        .setText(questionAndAnswer.getOptions().get(1).getOption());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return questionAndAnswerList.size();
    }

    public static class YesNoQuestionAnswerViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private RadioButton yes;
        private RadioButton no;

        public YesNoQuestionAnswerViewHolder(View itemView) {
            super(itemView);
            question = ButterKnife.findById(itemView, R.id.question);
            yes = ButterKnife.findById(itemView, R.id.answer_yes);
            no = ButterKnife.findById(itemView, R.id.answer_no);
        }
    }

    public static class SingleChoiceQuestionAnswerViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private RadioButton firstRadioBtn;
        private RadioButton secondRadioBtn;

        public SingleChoiceQuestionAnswerViewHolder(View itemView) {
            super(itemView);
            question = ButterKnife.findById(itemView, R.id.question);
            firstRadioBtn = ButterKnife.findById(itemView, R.id.first_radio_btn);
            secondRadioBtn = ButterKnife.findById(itemView, R.id.second_radio_btn);
        }
    }
}
