package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Option;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/10/17.
 */

public class QuestionAndAnswerAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<QuestionAndAnswer> questionAndAnswers;
    //using answer list to save the data when user change the answer
    //so that when user click submit button we can create object to save result.
    private List<Answer> answers;
    private Context context;

    public QuestionAndAnswerAdapter(Context context, List<QuestionAndAnswer> questionAndAnswers) {
        this.context = context;
        this.questionAndAnswers = questionAndAnswers;
        initAnswers(questionAndAnswers);
    }

    //init the answer list with data from question and answer list
    private void initAnswers(List<QuestionAndAnswer> questionAndAnswers) {
        this.answers = new ArrayList<>();
        if (questionAndAnswers.size() > 0) { //check if there're more than 0 question&answer
            for (int i = 0; i < questionAndAnswers.size(); i++) {
                QuestionAndAnswer questionAndAnswer = questionAndAnswers.get(i);
                List<Option> options = questionAndAnswer.getOptions();
                if (options != null && options.size() > 0) {//check if question have > 0 answer
                    int[] answerId = new int[1];
                    answerId[0] = options.get(0).getId();//init default answer check is the first option
                    answers.add(i, new Answer(questionAndAnswer.getQuestionId()
                            , answerId));
                }
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QuestionAndAnswer questionAndAnswer = questionAndAnswers.get(viewType);
        switch (questionAndAnswer.getQuestionType()) {
            case ViewConstant.TYPE_SINGLE_CHOICE:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.single_choice_question_answer, parent, false);
                return new SingleChoiceQuestionAnswerViewHolder(itemView);
            default:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.single_choice_question_answer, parent, false);
                return new SingleChoiceQuestionAnswerViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        QuestionAndAnswer questionAndAnswer = questionAndAnswers.get(position);
        switch (questionAndAnswer.getQuestionType()) {
            case ViewConstant.TYPE_SINGLE_CHOICE:
                SingleChoiceQuestionAnswerViewHolder viewHolder
                        = (SingleChoiceQuestionAnswerViewHolder) holder;
                viewHolder.question.setText(questionAndAnswer.getQuestionContent());
                List<Option> options = questionAndAnswer.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setId(i);
                    radioButton.setText(options.get(i).getOption());
                    viewHolder.radioGroup.addView(radioButton);
                }
                viewHolder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    final int[] answerId = new int[1];
                    answerId[0] = checkedId;
                    answers.get(position).setChoseAnswer(answerId);

                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return questionAndAnswers.size();
    }

    public static class SingleChoiceQuestionAnswerViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private RadioGroup radioGroup;

        public SingleChoiceQuestionAnswerViewHolder(View itemView) {
            super(itemView);
            question = ButterKnife.findById(itemView, R.id.question);
            radioGroup = ButterKnife.findById(itemView, R.id.radio_group);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
