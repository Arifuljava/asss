package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class Differnecedate extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
    EditText Email_Log;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView no_of_items, total_amount, spinner4;
    String package_name, package_price, packing;

    Button upgrade;
    KProgressHUD kProgressHUD;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_differnecedate);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Date Difference");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        //edit
        koto_roti=findViewById(R.id.koto_roti);
        spinner2=findViewById(R.id.spinner2);
        tips1=findViewById(R.id.tips1);
        result11=findViewById(R.id.result11);
        koto_point1=findViewById(R.id.koto_point1);
        people1=findViewById(R.id.people1);
        unit2=findViewById(R.id.unit2);

        spinner2.setOnItemSelectedListener(this);
        String[] textSizes = getResources().getStringArray(R.array.month);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.algeb, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

        koto_roti.setOnItemSelectedListener(this);;
        String[] textSizes1 = getResources().getStringArray(R.array.month);
        ArrayAdapter adapter1 = new ArrayAdapter(this,
                R.layout.algeb, textSizes1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        koto_roti.setAdapter(adapter);

        /////
        lastTouchAction = -1;
        koto_point1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int flag=0;
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){



                    koto_point1.addTextChangedListener(first);






                }
                lastTouchAction = event.getAction();

                return false;
            }
        });
        //second
        lastTouchAction = -1;
        people1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int flag=0;
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){



                    people1.addTextChangedListener(seocond);






                }
                lastTouchAction = event.getAction();

                return false;
            }
        });
        //third
        lastTouchAction = -1;
        tips1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int flag=0;
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){



                    tips1.addTextChangedListener(third);






                }
                lastTouchAction = event.getAction();

                return false;
            }
        });
        //forth
        lastTouchAction = -1;
        result11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int flag=0;
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){



                    result11.addTextChangedListener(forth);






                }
                lastTouchAction = event.getAction();

                return false;
            }
        });

    }
    TextWatcher forth=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if(TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())||
                        TextUtils.isEmpty(people1.getText().toString())||
                        TextUtils.isEmpty(tips1.getText().toString())||Integer.parseInt(koto_point1.getText().toString())>31||
                        Integer.parseInt(tips1.getText().toString())>31 ) {
                }
                else {
                    int currentDay = Integer.valueOf(koto_point1.getText().toString());
                    int currentMonth = Integer.valueOf(month);
                    int currentYear = Integer.valueOf(people1.getText().toString());

                    Date now = new Date(currentYear, currentMonth, currentDay);

                    int birthDay = Integer.valueOf(tips1.getText().toString());
                    int birthMonth = Integer.valueOf(month2);
                    int birthYear = Integer.valueOf(result11.getText().toString());
                    Date dob = new Date(birthYear, birthMonth, birthDay);

                    // days of every month
                    int month[] = {31, 28, 31, 30, 31, 30, 31,
                            31, 30, 31, 30, 31};

                    // if birth date is greater then current birth
                    // month then do not count this month and add 30
                    // to the date so as to subtract the date and
                    // get the remaining days
                    if (birthDay > currentDay) {
                        currentDay = currentDay + month[birthMonth - 1];
                        currentMonth = currentMonth - 1;
                    }

                    // if birth month exceeds current month, then do
                    // not count this year and add 12 to the month so
                    // that we can subtract and find out the difference
                    if (birthMonth > currentMonth) {
                        currentYear = currentYear - 1;
                        currentMonth = currentMonth + 12;
                    }

                    // calculate date, month, year
                    int calculated_date = currentDay - birthDay;
                    int calculated_month = currentMonth - birthMonth;
                    int calculated_year = currentYear - birthYear;
                    if (calculated_date<0) {
                        calculated_date=(calculated_date*(-1));
                    }
                    if (calculated_month<0) {
                        calculated_month=(calculated_month*(-1));
                    }
                    if (calculated_year<0) {
                        calculated_year=(calculated_year*(-1));
                    }
                    unit2.setText("Difference \n"+calculated_year+" Years, \n" +calculated_month+
                            " Months\n"+calculated_date+" Days.");


                }
            }
        }
    };
    TextWatcher third=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if(TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())||
                        TextUtils.isEmpty(people1.getText().toString())||
                        TextUtils.isEmpty(result11.getText().toString())||Integer.parseInt(koto_point1.getText().toString())>31||
                        Integer.parseInt(tips1.getText().toString())>31) {
                }
                else {
                    int currentDay = Integer.valueOf(koto_point1.getText().toString());
                    int currentMonth = Integer.valueOf(month);
                    int currentYear = Integer.valueOf(people1.getText().toString());

                    Date now = new Date(currentYear, currentMonth, currentDay);

                    int birthDay = Integer.valueOf(tips1.getText().toString());
                    int birthMonth = Integer.valueOf(month2);
                    int birthYear = Integer.valueOf(result11.getText().toString());
                    Date dob = new Date(birthYear, birthMonth, birthDay);

                    // days of every month
                    int month[] = {31, 28, 31, 30, 31, 30, 31,
                            31, 30, 31, 30, 31};

                    // if birth date is greater then current birth
                    // month then do not count this month and add 30
                    // to the date so as to subtract the date and
                    // get the remaining days
                    if (birthDay > currentDay) {
                        currentDay = currentDay + month[birthMonth - 1];
                        currentMonth = currentMonth - 1;
                    }

                    // if birth month exceeds current month, then do
                    // not count this year and add 12 to the month so
                    // that we can subtract and find out the difference
                    if (birthMonth > currentMonth) {
                        currentYear = currentYear - 1;
                        currentMonth = currentMonth + 12;
                    }

                    // calculate date, month, year
                    int calculated_date = currentDay - birthDay;
                    int calculated_month = currentMonth - birthMonth;
                    int calculated_year = currentYear - birthYear;
                    if (calculated_date<0) {
                        calculated_date=(calculated_date*(-1));
                    }
                    if (calculated_month<0) {
                        calculated_month=(calculated_month*(-1));
                    }
                    if (calculated_year<0) {
                        calculated_year=(calculated_year*(-1));
                    }
                    unit2.setText("Difference \n"+calculated_year+" Years, \n" +calculated_month+
                            " Months\n"+calculated_date+" Days.");


                }
            }
        }
    };
    TextWatcher seocond=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if(TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())||
                        TextUtils.isEmpty(tips1.getText().toString())||
                        TextUtils.isEmpty(result11.getText().toString())||Integer.parseInt(koto_point1.getText().toString())>31||
                        Integer.parseInt(tips1.getText().toString())>31) {
                }
                else {
                    //9-1-2023
                    int currentDay = Integer.valueOf(koto_point1.getText().toString());
                    int currentMonth = Integer.valueOf(month);
                    int currentYear = Integer.valueOf(people1.getText().toString());

                    Date now = new Date(currentYear, currentMonth, currentDay);
                    //5864256895
                    //24-10-1998

                    int birthDay = Integer.valueOf(tips1.getText().toString());
                    int birthMonth = Integer.valueOf(month2);
                    int birthYear = Integer.valueOf(result11.getText().toString());
                    Date dob = new Date(birthYear, birthMonth, birthDay);

                    // days of every month
                    // calculate date, month, year
                   /*
                    Calendar calendar=Calendar.getInstance();
                    int calenderyear=calendar.get(Calendar.YEAR);
                    if (calenderyear%4==0) {
                        int month[] = {31, 29, 31, 30, 31, 30, 31,
                                31, 30, 31, 30, 31};
                    }
                    else {
                        int month[] = {31, 28, 31, 30, 31, 30, 31,
                                31, 30, 31, 30, 31};
                    }
                    */

                    ///9-1-2023
                    //24-10-1998
                    int month[] = {31, 28, 31, 30, 31, 30, 31,
                            31, 30, 31, 30, 31};
                    // if birth date is greater then current birth
                    // month then do not count this month and add 30
                    // to the date so as to subtract the date and
                    // get the remaining days
                    if (birthDay > currentDay) {
                        //
                        currentDay = currentDay + month[birthMonth - 1];
                        currentMonth = currentMonth - 1;
                    }

                    // if birth month exceeds current month, then do
                    // not count this year and add 12 to the month so
                    // that we can subtract and find out the difference
                    if (birthMonth > currentMonth) {
                        currentYear = currentYear - 1;
                        currentMonth = currentMonth + 12;
                    }


                    int calculated_date = currentDay - birthDay;
                    int calculated_month = currentMonth - birthMonth;
                    int calculated_year = currentYear - birthYear;
                    if (calculated_date<0) {
                        calculated_date=(calculated_date*(-1));
                    }
                    if (calculated_month<0) {
                        calculated_month=(calculated_month*(-1));
                    }
                    if (calculated_year<0) {
                        calculated_year=(calculated_year*(-1));
                    }
                    unit2.setText("Difference \n"+calculated_year+" Years, \n" +calculated_month+
                            " Months\n"+calculated_date+" Days.");


                }
            }
        }
    };
    EditText unit2;
    TextWatcher first=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
String check=s.toString();
if(TextUtils.isEmpty(check)) {
}
else {
    if (TextUtils.isEmpty(people1.getText().toString())||
            TextUtils.isEmpty(tips1.getText().toString())||
            TextUtils.isEmpty(result11.getText().toString())||Integer.parseInt(koto_point1.getText().toString())>31||
            Integer.parseInt(tips1.getText().toString())>31) {
    }
    else {
        int currentDay = Integer.valueOf(koto_point1.getText().toString());
        int currentMonth = Integer.valueOf(month);
        int currentYear = Integer.valueOf(people1.getText().toString());

        Date now = new Date(currentYear, currentMonth, currentDay);

        int birthDay = Integer.valueOf(tips1.getText().toString());
        int birthMonth = Integer.valueOf(month2);
        int birthYear = Integer.valueOf(result11.getText().toString());
        Date dob = new Date(birthYear, birthMonth, birthDay);

        // days of every month
        int month[] = {31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31};

        // if birth date is greater then current birth
        // month then do not count this month and add 30
        // to the date so as to subtract the date and
        // get the remaining days
        if (birthDay > currentDay) {
            currentDay = currentDay + month[birthMonth - 1];
            currentMonth = currentMonth - 1;
        }

        // if birth month exceeds current month, then do
        // not count this year and add 12 to the month so
        // that we can subtract and find out the difference
        if (birthMonth > currentMonth) {
            currentYear = currentYear - 1;
            currentMonth = currentMonth + 12;
        }

        // calculate date, month, year
        int calculated_date = currentDay - birthDay;
        int calculated_month = currentMonth - birthMonth;
        int calculated_year = currentYear - birthYear;
        if (calculated_date<0) {
            calculated_date=(calculated_date*(-1));
        }
        if (calculated_month<0) {
            calculated_month=(calculated_month*(-1));
        }
        if (calculated_year<0) {
            calculated_year=(calculated_year*(-1));
        }
        unit2.setText("Difference \n"+calculated_year+" Years, \n" +calculated_month+
                " Months\n"+calculated_date+" Days.");


    }
}
        }
    };
    Spinner koto_roti,spinner2;
    int  lastTouchAction ;
    EditText koto_point1,result11,tips1,people1;

    EditText koto_point,people,tips,result1,result2,result3;
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }
String month,month2;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.koto_roti) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.equals("January")) {
                month="1";
            }
            else   if (valueFromSpinner.equals("February")) {
                month="2";
            }
            else   if (valueFromSpinner.equals("March")) {
                month="3";
            }
            else   if (valueFromSpinner.equals("April")) {
                month="4";
            }
            else   if (valueFromSpinner.equals("May")) {
                month="5";
            }
            else   if (valueFromSpinner.equals("June")) {
                month="6";
            }
            else   if (valueFromSpinner.equals("July")) {
                month="7";
            }
            else   if (valueFromSpinner.equals("August")) {
                month="8";
            }
            else   if (valueFromSpinner.equals("September")) {
                month="9";
            }
            else   if (valueFromSpinner.equals("October")) {
                month="10";
            }
            else   if (valueFromSpinner.equals("November")) {
                month="11";
            }
            else   if (valueFromSpinner.equals("December")) {
                month="12";
            }


        }
        else  if (parent.getId() == R.id.spinner2) {
            valueFromSpinner1 = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner1.equals("January")) {
                month2="1";
            }
            else   if (valueFromSpinner1.equals("February")) {
                month2="2";
            }
            else   if (valueFromSpinner1.equals("March")) {
                month2="3";
            }
            else   if (valueFromSpinner1.equals("April")) {
                month2="4";
            }
            else   if (valueFromSpinner1.equals("May")) {
                month2="5";
            }
            else   if (valueFromSpinner1.equals("June")) {
                month2="6";
            }
            else   if (valueFromSpinner1.equals("July")) {
                month2="7";
            }
            else   if (valueFromSpinner1.equals("August")) {
                month2="8";
            }
            else   if (valueFromSpinner1.equals("September")) {
                month2="9";
            }
            else   if (valueFromSpinner1.equals("October")) {
                month2="10";
            }
            else   if (valueFromSpinner1.equals("November")) {
                month2="11";
            }
            else   if (valueFromSpinner1.equals("December")) {
                month2="12";
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}