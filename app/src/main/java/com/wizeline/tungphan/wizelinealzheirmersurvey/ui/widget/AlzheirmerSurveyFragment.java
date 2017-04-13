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
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
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

    public static final String TAG = AlzheirmerSurveyFragment.class.getSimpleName();
    @Inject
    RxEventBus rxEventBus;
    private View view;
    @BindView(R.id.question_answer_rview)
    RecyclerView questionAnswerRView;
    @BindView(R.id.submit_button)
    Button submitButon;
    @BindView(R.id.guide_textview)
    TextView guideTextView;
    @BindView(R.id.patient_name)
    EditText patientNameEditText;
    private QuestionAndAnswerAdapter questionAndAnswerAdapter;
    //this field is using for switch this fragment to editable or un-editable
    private boolean editable;
    private PatientSurvey patientSurvey;
    private String patientName;

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientSurvey(PatientSurvey patientSurvey) {
        this.patientSurvey = patientSurvey;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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
        if (editable) {
            setupEditEnable();
        } else {
            setupViewOnly();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setQuestionAnswerRViewData(List<QuestionAndAnswer> questionAndAnswers) {
        questionAndAnswerAdapter = new QuestionAndAnswerAdapter(getContext()
                , questionAndAnswers, editable);
        if (!editable) {
            questionAndAnswerAdapter.setAnswers(patientSurvey.getAnswers());
        }
        questionAnswerRView.setAdapter(questionAndAnswerAdapter);
    }

    private void setupViewOnly() {
        submitButon.setVisibility(View.GONE);
        patientNameEditText.setClickable(false);
        patientNameEditText.setCursorVisible(false);
        patientNameEditText.setFocusable(false);
        patientNameEditText.setFocusableInTouchMode(false);
        guideTextView.setVisibility(View.GONE);
        patientNameEditText.setText(patientName);
    }

    void setupEditEnable() {
        submitButon.setVisibility(View.VISIBLE);
        patientNameEditText.setClickable(true);
        patientNameEditText.setCursorVisible(true);
        patientNameEditText.setFocusable(true);
        patientNameEditText.setFocusableInTouchMode(true);
        guideTextView.setVisibility(View.VISIBLE);
    }
}
