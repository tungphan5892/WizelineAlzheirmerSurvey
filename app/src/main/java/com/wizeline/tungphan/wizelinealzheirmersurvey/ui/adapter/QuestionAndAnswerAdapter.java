package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AnswerView;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.AnswerViewFactory;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/10/17.
 */

public class QuestionAndAnswerAdapter
        extends RecyclerView.Adapter<QuestionAndAnswerAdapter.QuestionAndAnswerViewHolder> {

    private List<QuestionAndAnswer> questionAndAnswerList;
    private Context context;

    public QuestionAndAnswerAdapter(Context context, List<QuestionAndAnswer> questionAndAnswerList) {
        this.context = context;
        this.questionAndAnswerList = questionAndAnswerList;
    }

    @Override
    public QuestionAndAnswerAdapter.QuestionAndAnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_answer_view, parent, false);
        return new QuestionAndAnswerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestionAndAnswerAdapter.QuestionAndAnswerViewHolder holder, int position) {
        QuestionAndAnswer questionAndAnswer = questionAndAnswerList.get(position);
        holder.question.setText(questionAndAnswer.getQuestion());
        AnswerView answerView = new AnswerViewFactory().getAnswerView(context, questionAndAnswer.getType());
        holder.answer.addView(answerView.createView());
    }

    @Override
    public int getItemCount() {
        return questionAndAnswerList.size();
    }

    public static class QuestionAndAnswerViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private FrameLayout answer;

        public QuestionAndAnswerViewHolder(View itemView) {
            super(itemView);
            question = ButterKnife.findById(itemView, R.id.question);
            answer = ButterKnife.findById(itemView, R.id.answer);
        }
    }
}
