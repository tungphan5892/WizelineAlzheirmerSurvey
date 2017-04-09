package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.survey;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;

/**
 * Created by tungphan on 4/8/17.
 */

public class AlzheirmerSurveyFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alzheirmer_survey_fragment, container, false);
        return view;
    }
}
