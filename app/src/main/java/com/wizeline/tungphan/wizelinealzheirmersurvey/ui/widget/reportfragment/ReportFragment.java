package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.reportfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.di.component.AppComponent;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter.AlzheirReportRViewAdapter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record.ReportPresenter;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.DividerItemDecoration;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment.BaseFragment;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wizeline.tungphan.wizelinealzheirmersurvey.constant.IntentConstant.START_SURVEY_ACTIVITY;

/**
 * Created by tungphan on 4/12/17.
 */

public class ReportFragment extends BaseFragment<ReportFragmentPresenter> implements ReportFragmentView{
    public static final String TAG = ReportFragment.class.getSimpleName();

    private View view;
    @BindView(R.id.report_recycview)
    RecyclerView reportRecycview;
    @BindView(R.id.parent_layout)
    FrameLayout parentLayout;
    private AlzheirReportRViewAdapter alzheirReportRViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alzheirmer_report_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        reportRecycview.setLayoutManager(linearLayoutManager);
        reportRecycview.addItemDecoration(new DividerItemDecoration(getContext()));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == START_SURVEY_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                getPresenter().getFirstReportData();
                Snackbar.make(parentLayout, R.string.notify_text_add_patient_survey_success
                        , Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(parentLayout, R.string.notify_text_add_patient_survey_fail
                        , Snackbar.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public int getAlzheirRecordAdapterSize() {
        return alzheirReportRViewAdapter.getItemCount();
    }

    public void getData(){
        getPresenter().getFirstReportData();
    }

    @Override
    public void onLoadFirstReportSuccess(Report report) {
        alzheirReportRViewAdapter = new AlzheirReportRViewAdapter(getContext()
                , report.getPatientSurveys());
        reportRecycview.setAdapter(alzheirReportRViewAdapter);
    }
}
