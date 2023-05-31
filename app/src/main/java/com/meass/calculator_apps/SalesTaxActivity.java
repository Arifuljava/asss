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

public class SalesTaxActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
    EditText Email_Log;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView no_of_items, total_amount, spinner4;
    String package_name, package_price, packing;
    EditText spinner1, spinner2;
    Button upgrade;
    KProgressHUD kProgressHUD;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_tax);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Sales Tax");
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

        tips1.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.tax);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.algeb, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tips1.setAdapter(adapter);



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




        result11tax=findViewById(R.id.result11tax);
        //forth
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
    }
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
                if (TextUtils.isEmpty(koto_point1.getText().toString())) {
                }
                else {
                    if (valueFromSpinner.toLowerCase().equals("add")) {
                        float percentage = Float.parseFloat(people1.getText().toString());
                        float dec = percentage / 100;
                        float total = dec * Float.parseFloat(koto_point1.getText().toString());


                        float sub=Float.parseFloat(koto_point1.getText().toString())+ total;
                        result11.setText(""+sub);
                        result11tax.setText(""+total);
                    }
                    else if (valueFromSpinner.toLowerCase().equals("subtraction")) {
                        float percentage = Float.parseFloat(people1.getText().toString());
                        float dec = percentage / 100;
                        float total = dec * Float.parseFloat(koto_point1.getText().toString());


                        float sub=Float.parseFloat(koto_point1.getText().toString().toString())- total;
                        result11.setText(""+sub);
                        result11tax.setText(""+total);
                    }
                }
            }
        }
    };
    EditText result11tax;
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
    if (TextUtils.isEmpty(people1.getText().toString())) {
    }
    else {
        if (valueFromSpinner.toLowerCase().equals("add")) {
            float percentage = Float.parseFloat(people1.getText().toString());
            float dec = percentage / 100;
            float total = dec * Float.parseFloat(koto_point1.getText().toString());


            float sub=Float.parseFloat(koto_point1.getText().toString())+ total;
            result11.setText(""+sub);
            result11tax.setText(""+total);
        }
        else if (valueFromSpinner.toLowerCase().equals("subtraction")) {
            float percentage = Float.parseFloat(people1.getText().toString());
            float dec = percentage / 100;
            float total = dec * Float.parseFloat(koto_point1.getText().toString());


            float sub=Float.parseFloat(koto_point1.getText().toString().toString())- total;
            result11.setText(""+sub);
            result11tax.setText(""+total);
        }
    }
}
        }
    };
    int  lastTouchAction ;
    Spinner tips1;
    EditText koto_point1,result11,people1;

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
        if (parent.getId() == R.id.tips1) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.toLowerCase().equals("add")) {
                if (TextUtils.isEmpty(people1.getText().toString())|| TextUtils.isEmpty(koto_point1.getText().toString())) {
                }
                else {
                    float percentage = Float.parseFloat(people1.getText().toString());
                    float dec = percentage / 100;
                    float total = dec * Float.parseFloat(koto_point1.getText().toString());


                    float sub=Float.parseFloat(koto_point1.getText().toString())+ total;
                    result11.setText(""+sub);
                    result11tax.setText(""+total);
                }


            }
            else if (valueFromSpinner.toLowerCase().equals("subtraction")) {
                if (TextUtils.isEmpty(people1.getText().toString())|| TextUtils.isEmpty(koto_point1.getText().toString())) {
                }
                else {
                    float percentage = Float.parseFloat(people1.getText().toString());
                    float dec = percentage / 100;
                    float total = dec * Float.parseFloat(koto_point1.getText().toString());


                    float sub=Float.parseFloat(koto_point1.getText().toString().toString())- total;
                    result11.setText(""+sub);
                    result11tax.setText(""+total);
                }

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}