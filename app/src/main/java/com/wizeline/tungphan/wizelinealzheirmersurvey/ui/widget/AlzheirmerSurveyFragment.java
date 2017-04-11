package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter.QuestionAndAnswerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/8/17.
 */

public class AlzheirmerSurveyFragment extends Fragment {

    public static final String TAG = AlzheirmerSurveyFragment.class.getSimpleName();
    private View view;
    @BindView(R.id.question_answer_rview)
    RecyclerView questionAnswerRView;
    private QuestionAndAnswerAdapter questionAndAnswerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alzheirmer_survey_fragment, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        questionAnswerRView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setQuestionAnswerRViewData(List<QuestionAndAnswer> questionAndAnswerList) {
        questionAndAnswerAdapter = new QuestionAndAnswerAdapter(getContext(), questionAndAnswerList);
        questionAnswerRView.setAdapter(questionAndAnswerAdapter);
    }
}
