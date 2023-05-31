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

public class PresureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presure);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Pressure");
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
                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();
                float kpr= (float) (Float.parseFloat(result11.getText().toString())*(0.07));
                koto_point1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(result11.getText().toString())*(0.07));
                people1.setText(""+fps);
                float mph= (float) (Float.parseFloat(result11.getText().toString())*(6894.76));
                tips1.setText(""+mph);

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
                float kpr= (float) (Float.parseFloat(tips1.getText().toString())*(0.000009875));
                koto_point1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(tips1.getText().toString())*(0.00001658));
                people1.setText(""+fps);
                float mph= (float) (Float.parseFloat(tips1.getText().toString())*(0.000145));
                result11.setText(""+mph);

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
                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();
                float kpr= (float) (Float.parseFloat(people1.getText().toString())*(0.99));
                koto_point1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(people1.getText().toString())*(100000));
                tips1.setText(""+fps);
                float mph= (float) (Float.parseFloat(people1.getText().toString())*(14.5));
                result11.setText(""+mph);

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
                float kpr= (float) (Float.parseFloat(koto_point1.getText().toString())*(1.01));
                people1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(koto_point1.getText().toString())*(101325));
                tips1.setText(""+fps);
                float mph= (float) (Float.parseFloat(koto_point1.getText().toString())*(14.7));
                result11.setText(""+mph);
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