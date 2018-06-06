package com.tripplanner.view;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tripplanner.R;
import com.tripplanner.controller.LunchController;
import com.tripplanner.model.LunchOption;
import com.tripplanner.model.RouteParam;
//import com.tripplanner.model.RouteParam;
//import com.tripplanner.model.LunchOption;

public class LunchActivity extends AppCompatActivity {

    private LunchController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        final TextView SetHourTitleTextView = (TextView) findViewById(R.id.textView_setHourTitle);
        SetHourTitleTextView.setText(R.string.set_hour_title);

        final TextView SetDurationTitleTextView = (TextView) findViewById(R.id.textView_setDuration);
        SetDurationTitleTextView.setText(R.string.set_duration_title);

        final TextView SetPlaceTitleTextView = (TextView) findViewById(R.id.textView_setPlace);
        SetPlaceTitleTextView.setText(R.string.set_place_title);


        RadioGroup radioGroup_Lunch = (RadioGroup) findViewById(R.id.radioGroup);

        RadioButton RadioButton_1=(RadioButton) findViewById(R.id.radioButton);
        RadioButton_1.setText(R.string.set_place_radio_button_1);

        RadioButton RadioButton_2=(RadioButton) findViewById(R.id.radioButton2);
        RadioButton_2.setText(R.string.set_place_radio_button_2);

        RadioButton RadioButton_3=(RadioButton) findViewById(R.id.radioButton3);
        RadioButton_3.setText(R.string.set_place_radio_button_3);

        controller = new LunchController(this);

        FloatingActionButton StartLunchButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_StartLunchHour);
        StartLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePicker(14,0,1);
            }
        });

        FloatingActionButton DurationLunchButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        DurationLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimePicker(1,0,2);
            }
        });
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    RouteParam.setLunchOption(LunchOption.exactPlace);
                    controller.startMapsActivity(1);
                break;
            case R.id.radioButton2:
                if (checked)
                    RouteParam.setLunchOption(LunchOption.placeType);
                    break;
            case R.id.radioButton3:
                if (checked)
                    RouteParam.setLunchOption(LunchOption.anyPlace);
                    break;
        }
    }


    public void openTimePicker(int currentHours, int currentMinutes, int id) {
        final Dialog dialog = new Dialog(LunchActivity.this);
        if(id==1)
            dialog.setTitle("Godzina rozpoczęcia");
        if(id==2)
            dialog.setTitle("Czas pobytu");
        dialog.setContentView(R.layout.time_picker_dialog);
        final NumberPicker numberPickerHour = (NumberPicker) dialog.findViewById(R.id.numberPickerHour);
        final NumberPicker numberPickerMin = (NumberPicker) dialog.findViewById(R.id.numberPickerMin);
        numberPickerHour.setMinValue(0);
        numberPickerHour.setMaxValue(23);
        numberPickerHour.setValue(currentHours);
        numberPickerMin.setMinValue(0);
        numberPickerMin.setMaxValue(59);
        numberPickerMin.setValue(currentMinutes);
        if(id == 1) {
            dialog.findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   //controller.setStartHour(numberPickerHour.getValue(), numberPickerMin.getValue());
                    dialog.dismiss();
                }
            });
        }
        if(id == 2){
            dialog.findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //controller.setDuration(numberPickerHour.getValue(), numberPickerMin.getValue());
                    dialog.dismiss();
                }
            });
        }
        dialog.show();
    }

}


