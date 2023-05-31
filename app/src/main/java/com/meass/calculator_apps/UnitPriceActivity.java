package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UnitPriceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_price);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Unit Price");
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



        lastTouchAction = -1;
        koto_point1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){
                    koto_point1.addTextChangedListener(first);

                }
                lastTouchAction = event.getAction();

                return false;
            }
        });
        //2
        people1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){

                    people1.addTextChangedListener(second);

                }
                lastTouchAction = event.getAction();

                return false;
            }
        });

        //third
        tips1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){

                    tips1.addTextChangedListener(third);

                }
                lastTouchAction = event.getAction();

                return false;
            }
        });

        //forth
        result11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP && lastTouchAction == MotionEvent.ACTION_DOWN){

                    result11.addTextChangedListener(forth);

                }
                lastTouchAction = event.getAction();

                return false;
            }
        });
        unit1=findViewById(R.id.unit1);
        unit2=findViewById(R.id.unit2);
    }
    int flag=0;
    float num1,num2;
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
                    koto_point1.setError("valid Price");
                }
                else {
                    if (flag==0) {
                        String first=koto_point1.getText().toString();
                        String second=people1.getText().toString();
                         num1=Float.parseFloat(first)/Float.parseFloat(second);
                        unit1.setText("Unit Price : "+num1);
                        flag++;

                    }
                    else if(flag==1) {
                        String first=koto_point1.getText().toString();
                        String second=people1.getText().toString();
                        num1=Float.parseFloat(first)/Float.parseFloat(second);
                        unit1.setText("Unit Price : "+num1);
                        if (num1<num2) {
                            unit1.setText("Best Price : "+num1);
                        }
                        else {
                            unit2.setText("Best Price : "+num2);
                        }
                    }

                }
            }

        }
    };
    //second
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
                    people1.setError(" Valid Quantity");
                }
                else {
                 //   Toast.makeText(UnitPriceActivity.this, "ggg"+flag, Toast.LENGTH_SHORT).show();
                    if (flag==0) {

                        String first=koto_point1.getText().toString();
                        String second=people1.getText().toString();
                        num1=Float.parseFloat(first)/Float.parseFloat(second);
                        unit1.setText("Unit Price : "+num1);
                        flag++;
                    }
                    else if(flag==1) {
                        //Toast.makeText(UnitPriceActivity.this, "ggg"+flag, Toast.LENGTH_SHORT).show();
                        String first=koto_point1.getText().toString();
                        String second=people1.getText().toString();
                        num1=Float.parseFloat(first)/Float.parseFloat(second);
                        unit1.setText("Unit Price : "+num1);
                        if (num1<num2) {
                            unit1.setText("Best Price : "+num1);
                        }
                        else {
                            unit2.setText("Best Price : "+num2);
                        }
                    }

                }
            }
        }
    };
    EditText unit1,unit2;
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
                if (TextUtils.isEmpty(result11.getText().toString())) {
                    tips1.setError("valid Price");
                }
                else {
                    if (flag==0) {
                        String first=tips1.getText().toString();
                        String second=result11.getText().toString();
                        num2=Float.parseFloat(first)/Float.parseFloat(second);
                        unit2.setText("Unit Price : "+num2);
                        flag++;
                    }
                    else if(flag==1) {
                        String first=tips1.getText().toString();
                        String second=result11.getText().toString();
                        num2=Float.parseFloat(first)/Float.parseFloat(second);
                        unit2.setText("Unit Price : "+num2);
                        if (num1<num2) {
                            unit1.setText("Best Price : "+num1);
                        }
                        else {
                            unit2.setText("Best Price : "+num2);
                        }
                    }

                }
            }
        }
    };
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
            if (TextUtils.isEmpty(check)) {
            }
            else {
                if (TextUtils.isEmpty(tips1.getText().toString())) {
                    result11.setError("valid Price");
                }
                else {
                    if (flag==0) {
                        String first=tips1.getText().toString();
                        String second=result11.getText().toString();
                        num2=Float.parseFloat(first)/Float.parseFloat(second);
                        unit2.setText("Unit Price : "+num2);
                        flag++;
                    }
                    else if(flag==1) {
                        String first=tips1.getText().toString();
                        String second=result11.getText().toString();
                        num2=Float.parseFloat(first)/Float.parseFloat(second);
                        unit2.setText("Unit Price : "+num2);
                        if (num1<num2) {
                            unit1.setText("Best Price : "+num1);
                        }
                        else {
                            unit2.setText("Best Price : "+num2);
                        }
                    }

                }
            }
        }
    };

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
}