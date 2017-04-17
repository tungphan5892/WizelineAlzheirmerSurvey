package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.support.v7.widget.RecyclerView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;

/**
 * Created by tungphan on 4/17/17.
 */

public interface QandAviewHolder {

    RecyclerView.ViewHolder makeViewHolder();

    void setEditable(boolean editable);

    Answer getAnswer();

}

