package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.PatientSurvey;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey.SurveyActivity;

import java.util.List;

import butterknife.ButterKnife;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.PATIENT_NAME;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.PATIENT_SURVEY_OBJECT;
import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.SURVEY_VIEW_ONLY;

/**
 * Created by tungphan on 4/12/17.
 */

public class AlzheirReportRViewAdapter
        extends RecyclerView.Adapter<AlzheirReportRViewAdapter.AlzheirRecordRViewViewHolder> {

    private List<PatientSurvey> patientSurveys;
    private Context context;

    public AlzheirReportRViewAdapter(Context context, List<PatientSurvey> patientSurveys) {
        this.context = context;
        this.patientSurveys = patientSurveys;
    }

    @Override
    public AlzheirReportRViewAdapter.AlzheirRecordRViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_item, parent, false);
        return new AlzheirRecordRViewViewHolder(itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(AlzheirReportRViewAdapter.AlzheirRecordRViewViewHolder holder, int position) {
        PatientSurvey patientSurvey = patientSurveys.get(position);
        holder.patientName.setText(patientSurvey.getPatientName());
        //TODO: display desease cause percent
//        float percent = 100 * patientSurvey.getDiseaseCausePercentage();
//        holder.diseaseCausePercent.setText(String.format("%.0f%%", percent));
        holder.recordItemLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, SurveyActivity.class);
            intent.putExtra(SURVEY_VIEW_ONLY, true);
            intent.putExtra(PATIENT_NAME, patientSurvey.getPatientName());
            intent.putExtra(PATIENT_SURVEY_OBJECT, patientSurvey);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return patientSurveys.size();
    }

    public static class AlzheirRecordRViewViewHolder extends RecyclerView.ViewHolder {

        private TextView patientName;
        private TextView diseaseCausePercent;
        private RelativeLayout recordItemLayout;

        public AlzheirRecordRViewViewHolder(View itemView) {
            super(itemView);
            patientName = ButterKnife.findById(itemView, R.id.patient_name);
            diseaseCausePercent = ButterKnife.findById(itemView, R.id.disease_cause_percentage);
            recordItemLayout = ButterKnife.findById(itemView, R.id.record_item_layout);
        }
    }

}
