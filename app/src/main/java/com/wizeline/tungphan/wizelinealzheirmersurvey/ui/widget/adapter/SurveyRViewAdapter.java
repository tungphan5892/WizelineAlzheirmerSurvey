package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.DateTimeQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.InputAnswerQandA;
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
    private boolean editable;
    private float diseaseCausePercentage = 0.0f;

    public List<QuestionAndAnswer> getQuestionAndAnswers() {
        return questionAndAnswers;
    }

    public SurveyRViewAdapter(Context context, List<QuestionAndAnswer> questionAndAnswers,
                              boolean editable) {
        this.context = context;
        this.questionAndAnswers = questionAndAnswers;
        this.editable = editable;
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
        qandAviewHolder.setEditable(editable);
        return qandAviewHolder.makeViewHolder();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        QuestionAndAnswer questionAndAnswer = questionAndAnswers.get(position);
        switch (questionAndAnswer.getQuestionType()) {
            case ViewConstant.SINGLE_CHOICE:
                SingleChoiceQandA singleChoiceQandA
                        = (SingleChoiceQandA) holder;
                singleChoiceQandA.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.MULTI_CHOICES:
                MultiChoicesQandA multiChoicesQandA
                        = (MultiChoicesQandA) holder;
                multiChoicesQandA.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.INPUT_ANSWER:
                InputAnswerQandA inputAnswerQandA
                        = (InputAnswerQandA) holder;
                inputAnswerQandA.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.DATETIME:
                DateTimeQandA dateTimeQandA
                        = (DateTimeQandA) holder;
                dateTimeQandA.bindViewHolder(questionAndAnswer);
                break;
            case ViewConstant.MIX_TYPE:
                MixTypeQandA mixTypeQandA
                        = (MixTypeQandA) holder;
                mixTypeQandA.bindViewHolder(questionAndAnswer);
                break;
            default:
                singleChoiceQandA = (SingleChoiceQandA) holder;
                singleChoiceQandA.bindViewHolder(questionAndAnswer);
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
