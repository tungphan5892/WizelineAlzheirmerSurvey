package com.wizeline.tungphan.wizelinealzheirmersurvey.ui.widget.viewholder;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wizeline.tungphan.wizelinealzheirmersurvey.R;
import com.wizeline.tungphan.wizelinealzheirmersurvey.common.Utils;
import com.wizeline.tungphan.wizelinealzheirmersurvey.constant.ViewConstant;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Answer;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.Option;
import com.wizeline.tungphan.wizelinealzheirmersurvey.model.QuestionAndAnswer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tungphan on 4/17/17.
 */

public abstract class LinearQandA extends RecyclerView.ViewHolder {

    protected Context context;
    protected TextView question;
    protected boolean editable;
    protected LinearLayout parentLayout;
    protected String questionId;
    protected List<Integer> choseAnswer = new ArrayList<>();
    protected List<String> inputAnswer = new ArrayList<>();
    protected List<Option> options = new ArrayList<>();

    public LinearQandA(Context context, View itemView) {
        super(itemView);
        this.context = context;
        parentLayout = (LinearLayout) itemView.findViewById(R.id.linear_qanda_parent);
        question = (TextView) itemView.findViewById(R.id.question);
    }

    protected View initView(Option option) {
        switch (option.getOptionType()) {
            case ViewConstant.OPTION_RADIO_BUTTON:
                return createRadioButton(option.getOption());
            case ViewConstant.OPTION_CHECKBOX:
                return createCheckbox(option.getOption());
            case ViewConstant.OPTION_EDITTEXT:
                return createEdittext(option.getOption());
            case ViewConstant.OPTION_DATETIME:
                return createDateTime(option.getOption());
            default:
                return createRadioButton(option.getOption());
        }
    }

    private RadioButton createRadioButton(String text) {
        RadioButton radioButton = new RadioButton(context);
        radioButton.setText(text);
        if (editable) {
            radioButton.setClickable(true);
        } else {
            radioButton.setClickable(false);
        }
        return radioButton;
    }

    private CheckBox createCheckbox(String text) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setText(text);
        if (editable) {
            checkBox.setClickable(true);
        } else {
            checkBox.setClickable(false);
        }
        return checkBox;
    }

    private EditText createEdittext(String text) {
        EditText editText = new EditText(context);
        if (editable) {
            setEnableEdittext(editText, true);
        } else {
            setEnableEdittext(editText, false);
            editText.setText(text);
        }
        return editText;
    }

    private void setEnableEdittext(EditText edittext, boolean enable) {
        edittext.setClickable(enable);
        edittext.setCursorVisible(enable);
        edittext.setFocusable(enable);
        edittext.setFocusableInTouchMode(enable);
    }

    private EditText createDateTime(String text) {
        EditText editText = new EditText(context);
        setEnableEdittext(editText, false);
        if (editable) {
            editText.setText(Utils.getDateTime());
        } else {
            editText.setHint(text);
        }
        editText.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        editText.setOnClickListener(this::showDatePickerDialog);
        return editText;
    }

    private void showDatePickerDialog(View dateView) {
        int day, month, year;
// Get Current Date
        final Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context
                , (view, year1, monthOfYear, dayOfMonth)
                -> ((EditText) dateView).setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1)
                , year, month, day);
        datePickerDialog.updateDate(year, month, day);
        datePickerDialog.show();
    }

    protected void processOutputFromView(Option option, int index) {
        switch (option.getOptionType()) {
            case ViewConstant.OPTION_RADIO_BUTTON:
                RadioButton radioButton = ((RadioButton) parentLayout.getChildAt(index));
                if (radioButton.isChecked()) {
                    choseAnswer.add(index);
                }
                break;
            case ViewConstant.OPTION_CHECKBOX:
                CheckBox checkBox = ((CheckBox) parentLayout.getChildAt(index));
                if (checkBox.isChecked()) {
                    choseAnswer.add(index);
                }
                break;
            case ViewConstant.OPTION_EDITTEXT:
                EditText editText = ((EditText) parentLayout.getChildAt(index));
                inputAnswer.add(editText.getText().toString());
                break;
            case ViewConstant.OPTION_DATETIME:
                editText = ((EditText) parentLayout.getChildAt(index));
                inputAnswer.add(editText.getText().toString());
                break;
            default:
                radioButton = ((RadioButton) parentLayout.getChildAt(index));
                if (radioButton.isChecked()) {
                    choseAnswer.add(index);
                }
                break;
        }
    }

    public abstract void setViewData(Answer answer);

    public abstract void bindViewHolder(QuestionAndAnswer questionAndAnswer);
}
