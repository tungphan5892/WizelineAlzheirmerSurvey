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
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.adapter.AlzheirRecordRViewAdapter;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tungphan on 4/12/17.
 */

public class AlzheirmerRecordFragment extends Fragment {
    public static final String TAG = AlzheirmerRecordFragment.class.getSimpleName();

    @Inject
    RxEventBus rxEventBus;
    private View view;
    @BindView(R.id.record_recycview)
    RecyclerView recordRecyclerView;
    private AlzheirRecordRViewAdapter alzheirRecordRViewAdapter;

    public void initInjector(Context context) {
        WizeApp.getAppComponent(context).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alzheirmer_record_fragment, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recordRecyclerView.setLayoutManager(linearLayoutManager);
        recordRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setRecordRecyclerViewData(Report report) {
        alzheirRecordRViewAdapter = new AlzheirRecordRViewAdapter(getContext(), report.getPatientSurveys());
        recordRecyclerView.setAdapter(alzheirRecordRViewAdapter);
    }
}
