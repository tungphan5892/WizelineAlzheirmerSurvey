package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.surveyfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.AppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.eventtype.IllegalInputEvent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Survey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.adapter.SurveyRViewAdapter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment.BaseFragment;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.DateTimeQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.InputAnswerQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.MixTypeQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.MultiChoicesQandA;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder.SingleChoiceQandA;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/8/17.
 */

public class SurveyFragment extends BaseFragment<SurveyFragmentPresenter> implements SurveyFragmentView {

    public static final String TAG = SurveyFragment.class.getSimpleName();
    private View view;
    @BindView(R.id.question_answer_rview)
    RecyclerView surveyRView;
    @BindView(R.id.submit_button)
    Button submitButon;
    @BindView(R.id.guide_textview)
    TextView guideTextView;
    @BindView(R.id.patient_name)
    EditText patientNameEditText;
    @BindView(R.id.parent_layout)
    RelativeLayout parentLayout;
    private SurveyRViewAdapter surveyRViewAdapter;
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

    private View.OnClickListener submitBtnClickListener = v -> {
        if (getIllegalInputType() == IllegalInputEvent.InputType.LEGAL) {
            getPresenter().savePatientSurveyToDatabase(getPatientSurveyFromInput(), surveyId);
        } else if (getIllegalInputType() == IllegalInputEvent.InputType.NAME_EDITTEXT) {
            Snackbar.make(parentLayout, R.string.text_require_name_entered
                    , Snackbar.LENGTH_LONG).show();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().loadSurveyFromLocal();
    }

    private PatientSurvey getPatientSurveyFromInput() {
        List<Answer> answers = new ArrayList<>();
        List<QuestionAndAnswer> questionAndAnswers = surveyRViewAdapter.getQuestionAndAnswers();
        for (int i = 0; i < surveyRViewAdapter.getItemCount(); i++) {
            RecyclerView.ViewHolder viewHolder
                    = surveyRView.getChildViewHolder(surveyRView.getChildAt(i));
            switch (questionAndAnswers.get(i).getQuestionType()) {
                case ViewConstant.SINGLE_CHOICE:
                    SingleChoiceQandA singleChoiceQandA
                            = (SingleChoiceQandA) viewHolder;
                    answers.add(singleChoiceQandA.getAnswer());
                    break;
                case ViewConstant.MULTI_CHOICES:
                    MultiChoicesQandA multiChoicesQandA
                            = (MultiChoicesQandA) viewHolder;
                    answers.add(multiChoicesQandA.getAnswer());
                    break;
                case ViewConstant.INPUT_ANSWER:
                    InputAnswerQandA inputAnswerQandA
                            = (InputAnswerQandA) viewHolder;
                    answers.add(inputAnswerQandA.getAnswer());
                    break;
                case ViewConstant.DATETIME:
                    DateTimeQandA dateTimeQandA
                            = (DateTimeQandA) viewHolder;
                    answers.add(dateTimeQandA.getAnswer());
                    break;
                case ViewConstant.MIX_TYPE:
                    MixTypeQandA mixTypeQandA
                            = (MixTypeQandA) viewHolder;
                    answers.add(mixTypeQandA.getAnswer());
                    break;
                default:
                    singleChoiceQandA = (SingleChoiceQandA) viewHolder;
                    answers.add(singleChoiceQandA.getAnswer());
            }
        }
        return new PatientSurvey(patientSurveyId
                , patientNameEditText.getText().toString()
                , answers);
    }

    private void addAnswer() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.survey_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        surveyRView.setLayoutManager(linearLayoutManager);
        submitButon.setOnClickListener(submitBtnClickListener);
        if (editable) {
            setupEditEnable();
        } else {
            setupViewOnly();
        }
        return view;
    }

    @Override
    protected void initInjector(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setSurveyRViewData(Survey survey) {
        surveyId = survey.getSurveyId();
        surveyRViewAdapter = new SurveyRViewAdapter(getContext()
                , survey.getQuestionAndAnswers(), editable);
        surveyRView.setAdapter(surveyRViewAdapter);
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
        return IllegalInputEvent.InputType.LEGAL;
    }

    @Override
    public void onLoadLocalSurveySuccess(Survey survey) {
        setSurveyRViewData(survey);
    }


    @Override
    public void onSavePatientSurveySuccess() {
        ((Activity) getContext()).setResult(Activity.RESULT_OK);
        ((Activity) getContext()).finish();
    }

    @Override
    public void onSavePatientSurveyFail() {
        ((Activity) getContext()).setResult(Activity.RESULT_CANCELED);
        ((Activity) getContext()).finish();
    }

}
