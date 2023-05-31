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

public class LoanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
        setContentView(R.layout.activity_loan);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Loan");
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
        spinner2=findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        result11tax=findViewById(R.id.result11tax);
        interestfff=findViewById(R.id.interestfff);

        String[] textSizes = getResources().getStringArray(R.array.periode);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.algeb, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        //first
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
                    if (valueFromSpinner.equals("Years Period")) {
                        float percentage = Float.parseFloat(people1.getText().toString());
                        float dec = percentage / 100;



                        float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
                        float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
                        float month=Float.parseFloat(tips1.getText().toString())*12;
                        float permonth=mainloan/month;
                        result11.setText(""+permonth);
                        result11tax.setText(""+mainloan);
                        interestfff.setText(""+maininterest);
                    }
                    else  if (valueFromSpinner.equals("Months Period")){
                        float percentage = Float.parseFloat(people1.getText().toString());
                        float dec = percentage / 100;



                        float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
                        float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
                        float month=Float.parseFloat(tips1.getText().toString());
                        float permonth=mainloan/month;
                        result11.setText(""+permonth);
                        result11tax.setText(""+mainloan);
                        interestfff.setText(""+maininterest);
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
                    if (valueFromSpinner.equals("Years Period")) {
                        float percentage = Float.parseFloat(people1.getText().toString());
                        float dec = percentage / 100;



                        float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
                        float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
                        float month=Float.parseFloat(tips1.getText().toString())*12;
                        float permonth=mainloan/month;
                        result11.setText(""+permonth);
                        result11tax.setText(""+mainloan);
                        interestfff.setText(""+maininterest);
                    }
                    else  if (valueFromSpinner.equals("Months Period")){
                        float percentage = Float.parseFloat(people1.getText().toString());
                        float dec = percentage / 100;



                        float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
                        float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
                        float month=Float.parseFloat(tips1.getText().toString());
                        float permonth=mainloan/month;
                        result11.setText(""+permonth);
                        result11tax.setText(""+mainloan);
                        interestfff.setText(""+maininterest);
                    }

                }
            }
        }
    };
    EditText result11tax,interestfff;
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
        if (valueFromSpinner.equals("Years Period")) {
            float percentage = Float.parseFloat(people1.getText().toString());
            float dec = percentage / 100;



            float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
            float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
            float month=Float.parseFloat(tips1.getText().toString())*12;
            float permonth=mainloan/month;
            result11.setText(""+permonth);
            result11tax.setText(""+mainloan);
            interestfff.setText(""+maininterest);
        }
        else  if (valueFromSpinner.equals("Months Period")){
            float percentage = Float.parseFloat(people1.getText().toString());
            float dec = percentage / 100;



            float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
            float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
            float month=Float.parseFloat(tips1.getText().toString());
            float permonth=mainloan/month;
            result11.setText(""+permonth);
            result11tax.setText(""+mainloan);
            interestfff.setText(""+maininterest);
        }

    }
}
        }
    };
    int  lastTouchAction ;
    Spinner spinner2;
    EditText koto_point1,result11,people1,tips1;

    EditText koto_point,people,tips,result1,result2,result3;
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner2) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.equals("Years Period")) {
                if (TextUtils.isEmpty(koto_point1.getText().toString())|| TextUtils.isEmpty(people1.getText().toString())
                || TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else {
                    float percentage = Float.parseFloat(people1.getText().toString());
                    float dec = percentage / 100;



                    float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
                    float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
                    float month=Float.parseFloat(tips1.getText().toString())*12;
                    float permonth=mainloan/month;
                    result11.setText(""+permonth);
                    result11tax.setText(""+mainloan);
                    interestfff.setText(""+maininterest);
                }

            }
            else  if (valueFromSpinner.equals("Months Period")){
                if (TextUtils.isEmpty(koto_point1.getText().toString())|| TextUtils.isEmpty(people1.getText().toString())
                        || TextUtils.isEmpty(tips1.getText().toString())) {
                }
                else {
                    float percentage = Float.parseFloat(people1.getText().toString());
                    float dec = percentage / 100;



                    float maininterest=Float.parseFloat(koto_point1.getText().toString())*dec*Float.parseFloat(tips1.getText().toString());
                    float mainloan=maininterest+Float.parseFloat(koto_point1.getText().toString());
                    float month=Float.parseFloat(tips1.getText().toString());
                    float permonth=mainloan/month;
                    result11.setText(""+permonth);
                    result11tax.setText(""+mainloan);
                    interestfff.setText(""+maininterest);
                }

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}