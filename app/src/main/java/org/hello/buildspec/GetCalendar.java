package org.hello.buildspec;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class GetCalendar extends Activity {
    String stringData;
    long tday;
    long day;
    String count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar c = Calendar.getInstance();

        int nYear = c.get(Calendar.YEAR);
        int nMon = c.get(Calendar.MONTH);
        int nDay = c.get(Calendar.DAY_OF_MONTH);
        tday = c.getTimeInMillis()/86400000;


        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        Calendar dday = Calendar.getInstance();
                        dday.set(year,monthOfYear,dayOfMonth);
                        day = dday.getTimeInMillis()/86400000;
                        long lcount = tday - day;

                        String strDate = String.valueOf(year) + ".";
                        strDate += String.valueOf(monthOfYear+1) + ".";
                        strDate += String.valueOf(dayOfMonth);
                        count ="D";
                        count += String.valueOf(lcount);

                        //
                        // fcm
                        // retrofit
                        // mvvm
                        //

                        Intent intent = getIntent();
                        stringData = strDate;
                        resultData();

                    }
                };

        DatePickerDialog oDialog = new DatePickerDialog(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                mDateSetListener, nYear, nMon, nDay);
        oDialog.show();

    }
    private void resultData() {
        Intent intent = new Intent();
        intent.putExtra("key", stringData);
        intent.putExtra("day", count);
        setResult(RESULT_OK, intent);
        finish();
    }

}
