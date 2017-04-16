package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype.IllegalInputEvent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype.SubmitSurveyEvent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
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
    @BindView(R.id.parent_layout)
    RelativeLayout parentLayout;
    private QuestionAndAnswerAdapter questionAndAnswerAdapter;
    //this field is using for switch this fragment to editable or un-editable
    private boolean editable;
    private PatientSurvey patientSurvey;
    private String surveyId;
    private String patientSurveyId;
    private String patientName;

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientSurvey(PatientSurvey patientSurvey) {
        this.patientSurvey = patientSurvey;
    }

    public void setPatientSurveyId(String patientSurveyId) {
        this.patientSurveyId = patientSurveyId;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void initInjector(Context context) {
        WizeApp.getAppComponent(context).inject(this);
    }

    private View.OnClickListener submitBtnClickListener = v -> {
        if (getIllegalInputType() == IllegalInputEvent.InputType.LEGAL) {
            Log.e(TAG, "submitBtnClickListener");
            sendSubmitSurveyEvent();
        } else {
            if (getIllegalInputType() == IllegalInputEvent.InputType.NAME_EDITTEXT) {
                Snackbar.make(parentLayout, R.string.text_require_name_entered
                        , Snackbar.LENGTH_LONG).show();
            } else if (getIllegalInputType() == IllegalInputEvent.InputType.NOT_INTERACTED) {
                showNotInteractedSnackbar();
            }
        }
    };

    private void showNotInteractedSnackbar() {
        Snackbar.make(parentLayout, R.string.warning_no_field_changed, Snackbar.LENGTH_LONG)
                .setAction(R.string.text_submit_button, view -> {
                    Log.e(TAG, "showNotInteractedSnackbar");
                    sendSubmitSurveyEvent();
                })
                .addCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        submitButon.setClickable(true);
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        submitButon.setClickable(false);
                    }
                }).show();
    }

    private PatientSurvey getPatientSurveyFromInput() {
        List<Answer> answers = questionAndAnswerAdapter.getAnswers();
        float deseaseCausePercent = questionAndAnswerAdapter.getDiseaseCausePercentage();
        return new PatientSurvey(patientSurveyId
                , patientNameEditText.getText().toString()
                , answers, deseaseCausePercent);
    }

    private void sendSubmitSurveyEvent() {
        Log.e(TAG, "sendSubmitSurveyEvent");
        rxEventBus.post(createSubmitSurveyEvent());
    }

    private SubmitSurveyEvent createSubmitSurveyEvent() {
        return new SubmitSurveyEvent(getPatientSurveyFromInput(), surveyId);
    }

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

    public void setQuestionAnswerRViewData(Survey survey) {
        surveyId = survey.getSurveyId();
        questionAndAnswerAdapter = new QuestionAndAnswerAdapter(getContext()
                , survey.getQuestionAndAnswers(), editable);
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

    private void setupEditEnable() {
        submitButon.setVisibility(View.VISIBLE);
        patientNameEditText.setClickable(true);
        patientNameEditText.setCursorVisible(true);
        patientNameEditText.setFocusable(true);
        patientNameEditText.setFocusableInTouchMode(true);
        guideTextView.setVisibility(View.VISIBLE);
    }

    private IllegalInputEvent.InputType getIllegalInputType() {
        if (patientNameEditText.getText().toString().equalsIgnoreCase("")) {
            return IllegalInputEvent.InputType.NAME_EDITTEXT;
        }
        if (!questionAndAnswerAdapter.isEdited()) {
            return IllegalInputEvent.InputType.NOT_INTERACTED;
        }
        return IllegalInputEvent.InputType.LEGAL;
    }
}
