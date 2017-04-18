package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.DateTimeQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.InputAnswerQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.LinearQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.MixTypeQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.MultiChoicesQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.QandAviewHolder;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.QandAviewholderFactory;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.SingleChoiceQandA;

import java.util.List;

/**
 * Created by tungphan on 4/10/17.
 */

public class SurveyRViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = SurveyRViewAdapter.class.getSimpleName();
    private Context context;
    private List<QuestionAndAnswer> questionAndAnswers;
    private PatientSurvey patientSurvey;
    private float diseaseCausePercentage = 0.0f;

    public List<QuestionAndAnswer> getQuestionAndAnswers() {
        return questionAndAnswers;
    }

    public SurveyRViewAdapter(Context context, List<QuestionAndAnswer> questionAndAnswers,
                              PatientSurvey patientSurvey) {
        this.context = context;
        this.questionAndAnswers = questionAndAnswers;
        this.patientSurvey = patientSurvey;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        QuestionAndAnswer questionAndAnswer = questionAndAnswers.get(position);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linear_qanda_view, parent, false);
        QandAviewholderFactory qandAviewholderFactory = new QandAviewholderFactory();
        QandAviewHolder qandAviewHolder = qandAviewholderFactory
                .getQandAviewHolder(context, questionAndAnswer.getQuestionType(), itemView);
        if (patientSurvey != null) {
            qandAviewHolder.setEditable(false);
        } else {
            qandAviewHolder.setEditable(true);
        }
        return qandAviewHolder.makeViewHolder();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final QuestionAndAnswer questionAndAnswer = questionAndAnswers.get(position);
        Answer answer = null;
        LinearQandA viewHolder = null;
        switch (questionAndAnswer.getQuestionType()) {
            case ViewConstant.SINGLE_CHOICE:
                viewHolder = (SingleChoiceQandA) holder;
                viewHolder.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.MULTI_CHOICES:
                viewHolder = (MultiChoicesQandA) holder;
                viewHolder.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.INPUT_ANSWER:
                viewHolder = (InputAnswerQandA) holder;
                viewHolder.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.DATETIME:
                viewHolder = (DateTimeQandA) holder;
                viewHolder.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.MIX_TYPE:
                viewHolder = (MixTypeQandA) holder;
                viewHolder.bindViewHolder(questionAndAnswer);
                break;
            default:
                viewHolder = (SingleChoiceQandA) holder;
                viewHolder.bindViewHolder(questionAndAnswer);
        }
        if (patientSurvey != null && patientSurvey.getAnswers() != null
                && patientSurvey.getAnswers().size() > 0) {
            answer = patientSurvey.getAnswers().get(position);
            viewHolder.setViewData(answer);
        }
    }

    @Override
    public int getItemCount() {
        return questionAndAnswers.size();
    }

    public float getDiseaseCausePercentage() {
        diseaseCausePercentage = 0.0f;
        //TODO: get disease Cause percentage
        return diseaseCausePercentage;
    }
}
