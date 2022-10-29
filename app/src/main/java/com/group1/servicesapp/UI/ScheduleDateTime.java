package com.group1.servicesapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.IOrderLogic;
import com.group1.servicesapp.logic.OrderLogic;

public class ScheduleDateTime extends Activity {
    private IOrderLogic orderLogic;

    private CalendarView calendarView;
    private TimePicker timePicker;
    private int currYear, currMonth, currDay, currHour, currMinute;
    //selected values
    private String sYear, sMonth, sDay, hour, minute;
    private int intYear, intMonth, intDay, intHour, intMinute;
    //default value
    private String service = "Service one";
    private String name = "Customer 3";
    private String[] serviceInfo = {"Provider A", "sample_category", "sample_price", "sample_location"};
    private String schedule = "null";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.calendar_and_clock);

        orderLogic = new OrderLogic(getApplicationContext());

        //fetch order from database
        Intent intent = getIntent();
        if( intent.getExtras() != null ){
            service = intent.getStringExtra("service");
            name = intent.getStringExtra("name");
            serviceInfo = intent.getStringArrayExtra("serviceInfo");
        }

        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        setInterval(timePicker);

        //set default date
        Calendar cal = Calendar.getInstance();
        sDay = String.valueOf( cal.get(Calendar.DAY_OF_MONTH) );
        sMonth = String.valueOf( cal.get(Calendar.MONTH) + 1 );
        sYear = String.valueOf( cal.get(Calendar.YEAR) );
        hour = String.valueOf( cal.get(Calendar.HOUR) );
        minute = String.valueOf( cal.get(Calendar.MINUTE) );

        //get current time, i.e. minimum scheduled time
        currDay = Integer.parseInt(sDay);
        currMonth = Integer.parseInt(sMonth);
        currYear = Integer.parseInt(sYear);
        currHour = Integer.parseInt(hour);
        currMinute = Integer.parseInt(minute);

        //get selected date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                sYear = String.valueOf(year);
                sMonth = String.valueOf(month + 1);
                sDay = String.valueOf(dayOfMonth);
            }
        });
    }


    private void setInterval(TimePicker timePicker) {
        NumberPicker minutePicker = timePicker.findViewById(Resources.getSystem().getIdentifier(
                "minute", "id", "android"));

        String[] display_minute = new String[] { "0", "30" };

        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(display_minute.length - 1);
        minutePicker.setDisplayedValues(display_minute);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cancel:
                Intent intent = new Intent(this, ServiceBrowse.class);
                intent.putExtra("SESSION_USER_ID",name);
                startActivity(intent);
                break;
            case R.id.next:
                setInfo();
                if( !validTime() )
                    futureTimeRequired(v);
                else if( intHour < 9 || intHour >= 21 )
                    showOpenHours(v);
                else if( !isAvailable() )
                    unavailable(v);
                else
                    pass();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setInfo(){
        intHour = timePicker.getHour();
        intMinute = timePicker.getMinute() * 30;
        hour = "0";
        minute = "0";

        if( intHour < 10 )
            hour += String.valueOf(intHour);
        else
            hour = String.valueOf(intHour);
        if( intMinute < 10 )
            minute += String.valueOf(intMinute);
        else
            minute = String.valueOf(intMinute);

        intMonth = Integer.parseInt(sMonth);
        intDay = Integer.parseInt(sDay);
        if( intMonth < 10 && sMonth.length() < 2 )
            sMonth = "0" + sMonth;
        if( intDay < 10 && sDay.length() < 2 )
            sDay = "0" + sDay;

        schedule = sYear + "/" + sMonth + "/" + sDay + "/" + hour + ":" + minute;
    }

    public void pass(){
        Intent intent = new Intent(this, OrderConfirm.class);
        intent.putExtra("service", service);
        intent.putExtra("name",name);
        intent.putExtra("schedule", schedule);
        intent.putExtra("serviceInfo", serviceInfo);
        startActivity(intent);
    }

    public void showOpenHours(View v){
        String msg = "Open hours: 9:00 a.m. to 9:00 p.m.";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

    public void futureTimeRequired(View v){
        String msg = "Please select future time";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

    public void unavailable(View v){
        String msg = "Provider unavailable at this time";
        Snackbar mySnackbar = Snackbar.make(v, msg, BaseTransientBottomBar.LENGTH_LONG);
        mySnackbar.show();
    }

    private boolean isAvailable(){
        boolean result = true;
        String provider = serviceInfo[0];
        result = orderLogic.available(schedule, provider);
        return result;
    }

    private boolean validDate(){
        boolean result = true;
        intYear = Integer.parseInt(sYear);

        if( intYear < currYear )
            result = false;     //cannot select past s
        else if( intYear == currYear && intMonth < currMonth )
            result = false;     //cannot select past months
        else if( intYear == currYear && intMonth == currMonth && intDay < currDay )
            result = false;     //cannot select past days

        return  result;
    }

    private boolean validTime(){
        boolean result = true;

        if( !validDate() )
            result = false;
        else if(intYear == currYear && intMonth == currMonth && intDay == currDay){
            if( intHour < currHour )
                result = false;
            else if( intHour == currHour && intMinute < currMinute )
                result = false;
        }

        return  result;
    }

}
