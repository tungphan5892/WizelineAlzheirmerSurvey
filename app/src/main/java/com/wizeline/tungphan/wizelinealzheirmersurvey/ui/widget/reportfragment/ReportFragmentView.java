package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.reportfragment;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.basefragment.BaseFragmentView;

/**
 * Created by tungphan on 4/17/17.
 */

public interface ReportFragmentView extends BaseFragmentView {

    void onLoadFirstReportSuccess(Report report);
}
