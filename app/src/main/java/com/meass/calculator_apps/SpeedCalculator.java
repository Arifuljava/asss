package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

public class SpeedCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Speed Calculator");
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
        koto_point=findViewById(R.id.koto_point);
        people=findViewById(R.id.people);
        tips=findViewById(R.id.tips);
        result1=findViewById(R.id.result1);
        koto_point.addTextChangedListener(meterpersencond);


        people.addTextChangedListener(kprwatcher);
        tips.addTextChangedListener(footwatcher);
        result1.addTextChangedListener(milperhourwatcher);


    }
    TextWatcher milperhourwatcher=new TextWatcher() {
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

                float mps= (float) (Float.parseFloat(result1.getText().toString())*(0.458));
                float fps= (float) (Float.parseFloat(result1.getText().toString())*(1.6125));
                float mph= (float) (Float.parseFloat(result1.getText().toString())*(1.4758));
                koto_point.setText(""+mps);

                people.setText(""+fps);
                tips.setText(""+mph);


            }
        }
    };
    TextWatcher footwatcher=new TextWatcher() {
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

                float mps= (float) (Float.parseFloat(tips.getText().toString())*(0.334));
                float fps= (float) (Float.parseFloat(tips.getText().toString())*(1.112));
                float mph= (float) (Float.parseFloat(tips.getText().toString())*(0.6897));
                koto_point.setText(""+mps);

                people.setText(""+fps);
                result1.setText(""+mph);


            }
        }
    };
    TextWatcher kprwatcher=new TextWatcher() {
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

                float mps= (float) (Float.parseFloat(people.getText().toString())/(0.277));
                float fps= (float) (Float.parseFloat(people.getText().toString())/(3.28));
                float mph= (float) ( Float.parseFloat(people.getText().toString())/(2.24));
                koto_point.setText(""+mps);

                tips.setText(""+fps);
                result1.setText(""+mph);


            }
        }
    };

    TextWatcher meterpersencond=new TextWatcher() {
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

    float kpr= (float) (Float.parseFloat(koto_point.getText().toString())*(3.6));
    people.setText(""+kpr);
   // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
    float fps= (float) (Float.parseFloat(koto_point.getText().toString())*(3.28));
    tips.setText(""+fps);
    float mph= (float) (Float.parseFloat(koto_point.getText().toString())*(2.24));
    result1.setText(""+mph);


}
        }
    };
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