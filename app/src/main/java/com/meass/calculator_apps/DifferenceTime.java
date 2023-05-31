package com.meass.calculator_apps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DifferenceTime extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
        setContentView(R.layout.activity_difference_time);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Time Difference");
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
        koto_roti = findViewById(R.id.koto_roti);
        spinner2 = findViewById(R.id.spinner2);
        tips1 = findViewById(R.id.tips1);
        result11 = findViewById(R.id.result11);
        koto_point1 = findViewById(R.id.koto_point1);
        people1 = findViewById(R.id.people1);
        unit2 = findViewById(R.id.unit2);

        spinner2.setOnItemSelectedListener(this);
        String[] textSizes = getResources().getStringArray(R.array.hour);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.algeb, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

        koto_roti.setOnItemSelectedListener(this);
        ;
        String[] textSizes1 = getResources().getStringArray(R.array.hour);
        ArrayAdapter adapter1 = new ArrayAdapter(this,
                R.layout.algeb, textSizes1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        koto_roti.setAdapter(adapter);

        /////
        lastTouchAction = -1;
        koto_point1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int flag = 0;
                if (event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN) {


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
                int flag = 0;
                if (event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN) {


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
                int flag = 0;
                if (event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN) {


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
                int flag = 0;
                if (event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN) {


                    result11.addTextChangedListener(forth);


                }
                lastTouchAction = event.getAction();

                return false;
            }
        });

    }
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

        }
        else  if (parent.getId() == R.id.spinner2) {
            valueFromSpinner1 = parent.getItemAtPosition(position).toString();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    TextWatcher forth=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if(TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())||
                        TextUtils.isEmpty(people1.getText().toString())||
                        TextUtils.isEmpty(tips1.getText().toString())||Integer.parseInt(koto_point1.getText().toString())>60||
                        Integer.parseInt(tips1.getText().toString())>60 ||Integer.parseInt(people1.getText().toString())>24
                ||Integer.parseInt(result11.getText().toString())>24) {
                }
                else {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    Date startDate = null;
                    try {
                        startDate = simpleDateFormat.parse(people1.getText().toString()+":"+koto_point1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date endDate = null;
                    try {
                        endDate = simpleDateFormat.parse(result11.getText().toString()+":"+tips1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long difference = endDate.getTime() - startDate.getTime();
                    if(difference<0)
                    {
                        Date dateMax = null;
                        try {
                            dateMax = simpleDateFormat.parse("24:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date dateMin = null;
                        try {
                            dateMin = simpleDateFormat.parse("00:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
                    }
                    int days = (int) (difference / (1000*60*60*24));
                    int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                    int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                    //Log.i("======= Hours"," :: "+hours);
                    unit2.setText("Difference \n" +
                            ""+days+" Days \n"+hours+" Hours "+"\n"+hours+" Minutes");

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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if(TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())||
                        TextUtils.isEmpty(people1.getText().toString())||
                        TextUtils.isEmpty(result11.getText().toString()) ||Integer.parseInt(koto_point1.getText().toString())>60||
                        Integer.parseInt(tips1.getText().toString())>60 ||Integer.parseInt(people1.getText().toString())>24
                        ||Integer.parseInt(result11.getText().toString())>24) {
                }
                else {

                  //  DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

                    //String startTime = people1.getText().toString()+":"+koto_point1.getText().toString()+" "+valueFromSpinner;
                   /// String endTime = result11.getText().toString()+":"+tips1.getText().toString()+" "+valueFromSpinner1;

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    Date startDate = null;
                    try {
                        startDate = simpleDateFormat.parse(people1.getText().toString()+":"+koto_point1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date endDate = null;
                    try {
                        endDate = simpleDateFormat.parse(result11.getText().toString()+":"+tips1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long difference = endDate.getTime() - startDate.getTime();
                    if(difference<0)
                    {
                        Date dateMax = null;
                        try {
                            dateMax = simpleDateFormat.parse("24:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date dateMin = null;
                        try {
                            dateMin = simpleDateFormat.parse("00:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
                    }
                    int days = (int) (difference / (1000*60*60*24));
                    int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                    int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                    //Log.i("======= Hours"," :: "+hours);
                    unit2.setText("Difference \n" +
                            ""+days+" Days \n"+hours+" Hours "+"\n"+hours+" Minutes");

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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if(TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())||
                        TextUtils.isEmpty(tips1.getText().toString())||
                        TextUtils.isEmpty(result11.getText().toString()) ||Integer.parseInt(koto_point1.getText().toString())>60||
                        Integer.parseInt(tips1.getText().toString())>60 ||Integer.parseInt(people1.getText().toString())>24
                        ||Integer.parseInt(result11.getText().toString())>24) {
                }
                else {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    Date startDate = null;
                    try {
                        startDate = simpleDateFormat.parse(people1.getText().toString()+":"+koto_point1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date endDate = null;
                    try {
                        endDate = simpleDateFormat.parse(result11.getText().toString()+":"+tips1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long difference = endDate.getTime() - startDate.getTime();
                    if(difference<0)
                    {
                        Date dateMax = null;
                        try {
                            dateMax = simpleDateFormat.parse("24:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date dateMin = null;
                        try {
                            dateMin = simpleDateFormat.parse("00:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
                    }
                    int days = (int) (difference / (1000*60*60*24));
                    int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                    int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                    //Log.i("======= Hours"," :: "+hours);
                    unit2.setText("Difference \n" +
                            ""+days+" Days \n"+hours+" Hours "+"\n"+hours+" Minutes");

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

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if(TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(people1.getText().toString())||
                        TextUtils.isEmpty(tips1.getText().toString())||
                        TextUtils.isEmpty(result11.getText().toString()) ||Integer.parseInt(koto_point1.getText().toString())>60||
                        Integer.parseInt(tips1.getText().toString())>60 ||Integer.parseInt(people1.getText().toString())>24
                        ||Integer.parseInt(result11.getText().toString())>24) {
                }
                else {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    Date startDate = null;
                    try {
                        startDate = simpleDateFormat.parse(people1.getText().toString()+":"+koto_point1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date endDate = null;
                    try {
                        endDate = simpleDateFormat.parse(result11.getText().toString()+":"+tips1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long difference = endDate.getTime() - startDate.getTime();
                    if(difference<0)
                    {
                        Date dateMax = null;
                        try {
                            dateMax = simpleDateFormat.parse("24:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date dateMin = null;
                        try {
                            dateMin = simpleDateFormat.parse("00:00");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        difference=(dateMax.getTime() -startDate.getTime() )+(endDate.getTime()-dateMin.getTime());
                    }
                    int days = (int) (difference / (1000*60*60*24));
                    int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                    int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                    //Log.i("======= Hours"," :: "+hours);
                    unit2.setText("Difference \n" +
                            ""+days+" Days \n"+hours+" Hours "+"\n"+hours+" Minutes");

                }
            }
        }
    };
}