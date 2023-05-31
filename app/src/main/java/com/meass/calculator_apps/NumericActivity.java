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

import es.dmoral.toasty.Toasty;

public class NumericActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeric);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Numerical Exchange");
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


                    people1.setText("");
                    tips1.setText("");
                    result11.setText("");
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
                    koto_point1.setText("");

                    tips1.setText("");
                    result11.setText("");
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
                    koto_point1.setText("");
                    people1.setText("");

                    result11.setText("");
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
                    koto_point1.setText("");
                    people1.setText("");
                    tips1.setText("");

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
            String check11=s.toString();
            if (TextUtils.isEmpty(check11)) {

            }
            else {
                if(check11.contains("2")||check11.contains("3")||check11.contains("4")||check11.contains("5")||
                        check11.contains("6")||check11.contains("7")||check11.contains("8")||
                        check11.contains("9")||check11.contains("0")||check11.contains("1")||check11.contains("a")||
                        check11.contains("A")||check11.contains("b")||check11.contains("B")||check11.contains("c")||
                        check11.contains("C")||check11.contains("d")||check11.contains("D")||check11.contains("e")||check11.contains("E")||
                        check11.contains("f")||check11.contains("F"))
                {
                    long kpr= Long.parseLong(tips1.getText().toString(),16);
                    koto_point1.setText(""+Long.toBinaryString(kpr));
                    // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                    long fps= Long.parseLong(tips1.getText().toString(),16);
                    people1.setText(""+Long.toOctalString(fps));
                    long mph= Long.parseLong(tips1.getText().toString(),16);
                    tips1.setText(""+mph);
                }
                else {
                    Toasty.info(getApplicationContext(),"Enter a valid number.",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();


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
            String check11=s.toString();
            if (TextUtils.isEmpty(check11)) {

            }
            else {
                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();
                long kpr= Long.parseLong(tips1.getText().toString(),10);
                koto_point1.setText(""+Long.toBinaryString(kpr));
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                long fps= Long.parseLong(tips1.getText().toString(),10);
                people1.setText(""+Long.toOctalString(fps));
                long mph= Long.parseLong(tips1.getText().toString(),10);
                result11.setText(""+Long.toHexString(mph));

            }
        }
    };
    int  lastTouchAction ;
    TextWatcher second=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check11=s.toString();
            if (TextUtils.isEmpty(check11)) {

            }
            else {
                if(check11.contains("8")||
                        check11.contains("9")) {
                    Toasty.info(getApplicationContext(),"Enter a valid number.",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                {
                    long kpr= Long.parseLong(people1.getText().toString(),8);
                    koto_point1.setText(""+Long.toBinaryString(kpr));
                    // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                    long fps= Long.parseLong(people1.getText().toString(),8);
                    tips1.setText(""+fps);
                    long mph= Long.parseLong(people1.getText().toString(),8);
                    result11.setText(""+Long.toHexString(mph));
                }
                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();


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
                if(check.contains("2")||check.contains("3")||check.contains("4")||check.contains("5")||
                        check.contains("6")||check.contains("7")||check.contains("8")||
                        check.contains("9")) {
                    Toasty.info(getApplicationContext(),"Enter a valid number.",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
               else {
                    long kpr= Long.parseLong(koto_point1.getText().toString(),2);
                    people1.setText(""+Long.toOctalString(kpr));
                    // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                    long  fps= Long.parseLong(koto_point1.getText().toString(),2);
                    tips1.setText(""+fps);
                    long mph= Long.parseLong(koto_point1.getText().toString(),2);
                    result11.setText(""+Long.toHexString(mph));
                }


            }
        }
    };
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
