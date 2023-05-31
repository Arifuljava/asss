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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

public class HealthCaloric extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
        setContentView(R.layout.activity_health_caloric);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Daily Caloric Burn");
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

        //  koto_point.addTextChangedListener(first);
        // people.addTextChangedListener(second);
        koto_point1=findViewById(R.id.koto_point1);
        people1=findViewById(R.id.people1);
        tips1=findViewById(R.id.tips1);
        result11=findViewById(R.id.result11);

        result11tax=findViewById(R.id.result11tax);
        interestfff=findViewById(R.id.interestfff);

        interestfff.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.sex);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.algeb, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestfff.setAdapter(adapter);


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
        people1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int flag=0;
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){



                    people1.addTextChangedListener(second);






                }
                lastTouchAction = event.getAction();

                return false;
            }
        });
        //thied
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
    }
    EditText result11tax;
    Spinner interestfff;
    int  lastTouchAction ;
    Spinner spinner2;
    EditText koto_point1,result11,people1,tips1;

    EditText koto_point,people,tips,result1,result2,result3;
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));

        return true;
    }
    //third
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
            if (TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())&& TextUtils.isEmpty(people1.getText().toString())) {
                }
                else  if (TextUtils.isEmpty(koto_point1.getText().toString())|| TextUtils.isEmpty(people1.getText().toString())) {
                }
                else {
                    if (valueFromSpinner.equals("Male")) {
                        float first= (float) (Float.parseFloat(tips1.getText().toString())*6.2);
                        float  second=(float) (Float.parseFloat(people1.getText().toString())*12.7);
                        float third=(float) (Float.parseFloat(koto_point1.getText().toString())*6.76);
                        float temp=(first+second)-third;
                        float main=66+temp;
                        result11tax.setText(""+main);

                    }
                    else  if (valueFromSpinner.equals("Female")){
                        float first= (float) (Float.parseFloat(tips1.getText().toString())*4.35);
                        float  second=(float) (Float.parseFloat(people1.getText().toString())*4.7);
                        float third=(float) (Float.parseFloat(koto_point1.getText().toString())*4.7);
                        float temp=(first+second)-third;
                        float main= (float) (655.1+temp);
                        result11tax.setText(""+main);

                    }

                }
            }
        }
    };
    ///second
    TextWatcher second=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check=s.toString();
            if (TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(koto_point1.getText().toString())&& TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else  if (TextUtils.isEmpty(koto_point1.getText().toString())|| TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else {
                    if (valueFromSpinner.equals("Male")) {
                        float first= (float) (Float.parseFloat(tips1.getText().toString())*6.2);
                        float  second=(float) (Float.parseFloat(people1.getText().toString())*12.7);
                        float third=(float) (Float.parseFloat(koto_point1.getText().toString())*6.76);
                        float temp=(first+second)-third;
                        float main=66+temp;
                        result11tax.setText(""+main);

                    }
                    else  if (valueFromSpinner.equals("Female")){
                        float first= (float) (Float.parseFloat(tips1.getText().toString())*4.35);
                        float  second=(float) (Float.parseFloat(people1.getText().toString())*4.7);
                        float third=(float) (Float.parseFloat(koto_point1.getText().toString())*4.7);
                        float temp=(first+second)-third;
                        float main= (float) (655.1+temp);
                        result11tax.setText(""+main);

                    }


                }
            }
        }
    };

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
            if (TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(people1.getText().toString())&& TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else  if (TextUtils.isEmpty(people1.getText().toString())|| TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else {
                    if (valueFromSpinner.equals("Male")) {
                        float first= (float) (Float.parseFloat(tips1.getText().toString())*6.2);
                        float  second=(float) (Float.parseFloat(people1.getText().toString())*12.7);
                        float third=(float) (Float.parseFloat(koto_point1.getText().toString())*6.76);
                        float temp=(first+second)-third;
                        float main=66+temp;
                        result11tax.setText(""+main);

                    }
                    else  if (valueFromSpinner.equals("Female")){
                        float first= (float) (Float.parseFloat(tips1.getText().toString())*4.35);
                        float  second=(float) (Float.parseFloat(people1.getText().toString())*4.7);
                        float third=(float) (Float.parseFloat(koto_point1.getText().toString())*4.7);
                        float temp=(first+second)-third;
                        float main= (float) (655.1+temp);
                        result11tax.setText(""+main);

                    }


                }
            }
        }
    };
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.interestfff) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.equals("Male")) {
                if (TextUtils.isEmpty(koto_point1.getText().toString())|| TextUtils.isEmpty(people1.getText().toString())
                        || TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else {
                    float first= (float) (Float.parseFloat(tips1.getText().toString())*6.2);
                    float  second=(float) (Float.parseFloat(people1.getText().toString())*12.7);
                    float third=(float) (Float.parseFloat(koto_point1.getText().toString())*6.76);
                    float temp=(first+second)-third;
                    float main=66+temp;
                    result11tax.setText(""+main);
                }


            }
            else  if (valueFromSpinner.equals("Female")){
                if (TextUtils.isEmpty(koto_point1.getText().toString())|| TextUtils.isEmpty(people1.getText().toString())
                        || TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else {
                    float first= (float) (Float.parseFloat(tips1.getText().toString())*4.35);
                    float  second=(float) (Float.parseFloat(people1.getText().toString())*4.7);
                    float third=(float) (Float.parseFloat(koto_point1.getText().toString())*4.7);
                    float temp=(first+second)-third;
                    float main= (float) (655.1+temp);
                    result11tax.setText(""+main);
                }


            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}