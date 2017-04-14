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

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.WizeApp;
import com.wizeline.tungphan.wizelinealzheirmersurvey.eventbus.RxEventBus;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter.AlzheirReportRViewAdapter;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/12/17.
 */

public class AlzheirmerReportFragment extends Fragment {
    public static final String TAG = AlzheirmerReportFragment.class.getSimpleName();

    @Inject
    RxEventBus rxEventBus;
    private View view;
    @BindView(R.id.report_recycview)
    RecyclerView reportRecycview;
    private AlzheirReportRViewAdapter alzheirReportRViewAdapter;

    public void initInjector(Context context) {
        WizeApp.getAppComponent(context).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alzheirmer_report_fragment, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        reportRecycview.setLayoutManager(linearLayoutManager);
        reportRecycview.addItemDecoration(new DividerItemDecoration(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setRecordRecyclerViewData(Report report) {
        alzheirReportRViewAdapter = new AlzheirReportRViewAdapter(getContext(), report.getPatientSurveys());
        reportRecycview.setAdapter(alzheirReportRViewAdapter);
    }

    public int getAlzheirRecordAdapterSize(){
        return alzheirReportRViewAdapter.getItemCount();
    }
}
