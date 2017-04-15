package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.record;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Report;
import com.wizeline.tungphan.wizelinealzheirmersurvey.ui.slidemenu.SlideMenuView;

/**
 * Created by tungphan on 4/9/17.
 */

public interface ReportView extends SlideMenuView {

    void onLoadReportFromDatabaseSuccess(Report report);

    void onSaveAssetFileComplete();

    void onSaveAssetFileFailed();

}
