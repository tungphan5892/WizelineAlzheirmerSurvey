package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.EventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype.SubmitSurveyEvent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter.QuestionAndAnswerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/8/17.
 */

public class AlzheirmerSurveyFragment extends Fragment {

    @Inject
    RxEventBus rxEventBus;
    public static final String TAG = AlzheirmerSurveyFragment.class.getSimpleName();
    private View view;
    @BindView(R.id.question_answer_rview)
    RecyclerView questionAnswerRView;
    @BindView(R.id.submit_button)
    Button submitButon;
    @BindView(R.id.patient_name)
    EditText patientNameEditText;
    private QuestionAndAnswerAdapter questionAndAnswerAdapter;

    public AlzheirmerSurveyFragment(Context context) {
        initInjector(context);
    }

    public void initInjector(Context context) {
        WizeApp.getAppComponent(context).inject(this);
    }


    private View.OnClickListener submitBtnClickListener = v -> {
        List<Answer> answers = questionAndAnswerAdapter.getAnswers();
        //TODO: caculate the disease percentage if neccessary
        PatientSurvey patientSurvey = new PatientSurvey(patientNameEditText.getText().toString(),
                answers, 0.0f);
        rxEventBus.post(new SubmitSurveyEvent(patientSurvey));
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alzheirmer_survey_fragment, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        questionAnswerRView.setLayoutManager(linearLayoutManager);
        submitButon.setOnClickListener(submitBtnClickListener);
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
