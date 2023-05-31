package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TipCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Tip Calculator");
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
        result2=findViewById(R.id.result2);
        result3=findViewById(R.id.result3);

        koto_point.addTextChangedListener(finalbillwatcher);
        tips.addTextChangedListener(finaltipwatcher);
        people.addTextChangedListener(peoplewatcher);
        main_constraint=findViewById(R.id.mainnn);
        topAnimation = AnimationUtils.loadAnimation(TipCalculator.this, R.anim.splash_top_animation);
      main_constraint.setAnimation(topAnimation);

    }
    //Other Variables
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    LinearLayout main_constraint;
    TextWatcher peoplewatcher=new TextWatcher() {
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
    if (TextUtils.isEmpty(koto_point.getText().toString())) {

    }
    else  if (!TextUtils.isEmpty(koto_point.getText().toString())) {
        if (TextUtils.isEmpty(tips.getText().toString())) {
            float ddd=Float.parseFloat(koto_point.getText().toString());
            finallbil=ddd+((ddd*10)/100);
            float total=((ddd*10)/100);
            result3.setText(""+total);
            result1.setText(""+finallbil);
            result2.setText("0");
        }
        else if (!TextUtils.isEmpty(tips.getText().toString())){
            if (TextUtils.isEmpty(people.getText().toString())) {
                float percentage = Float.parseFloat(koto_point.getText().toString());
                float dec = percentage / 100;
                float total = dec * Float.parseFloat(tips.getText().toString().toString());
                finallbil=percentage+total;
                result3.setText(""+total);
                result1.setText(""+finallbil);
                result2.setText("0");
            }
            else if(!TextUtils.isEmpty(people.getText().toString())) {
                float percentage = Float.parseFloat(koto_point.getText().toString());
                float dec = percentage / 100;
                float total = dec * Float.parseFloat(tips.getText().toString().toString());
                finallbil=percentage+total;
                result3.setText(""+total);
                float dd=finallbil/(Float.parseFloat(people.getText().toString()));
                result2.setText(""+dd);
                result1.setText(""+finallbil);
            }
        }

    }
    else   if (TextUtils.isEmpty(tips.getText().toString())) {
    }
    else  if (!TextUtils.isEmpty(tips.getText().toString())) {
        if (TextUtils.isEmpty(koto_point.getText().toString())) {

        }
        else if (!TextUtils.isEmpty(koto_point.getText().toString())){
            if (TextUtils.isEmpty(people.getText().toString())) {
                float percentage = Float.parseFloat(koto_point.getText().toString());
                float dec = percentage / 100;
                float total = dec * Float.parseFloat(tips.getText().toString().toString());
                finallbil=percentage+total;
                result3.setText(""+total);
                result2.setText("0");
                result1.setText(""+finallbil);
            }
            else if(!TextUtils.isEmpty(people.getText().toString())) {
                float percentage = Float.parseFloat(koto_point.getText().toString());
                float dec = percentage / 100;
                float total = dec * Float.parseFloat(tips.getText().toString().toString());
                finallbil=percentage+total;
                result3.setText(""+total);
                float dd=finallbil/(Float.parseFloat(people.getText().toString()));
                result2.setText(""+dd);
                result1.setText(""+finallbil);
            }
        }
    }
    else {
        float every=finallbil/Float.parseFloat(check);
        result2.setText(""+every);
    }
}
        }
    };
    TextWatcher finaltipwatcher=new TextWatcher() {
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
    if (TextUtils.isEmpty(koto_point.getText().toString())) {

    }
    else if (!TextUtils.isEmpty(koto_point.getText().toString())){
       if (TextUtils.isEmpty(people.getText().toString())) {
           float percentage = Float.parseFloat(koto_point.getText().toString());
           float dec = percentage / 100;
           float total = dec * Float.parseFloat(tips.getText().toString().toString());
           finallbil=percentage+total;
           result3.setText(""+total);
           result2.setText("0");
           result1.setText(""+finallbil);
       }
       else if(!TextUtils.isEmpty(people.getText().toString())) {
           float percentage = Float.parseFloat(koto_point.getText().toString());
           float dec = percentage / 100;
           float total = dec * Float.parseFloat(tips.getText().toString().toString());
           finallbil=percentage+total;
           result3.setText(""+total);
           float dd=finallbil/(Float.parseFloat(people.getText().toString()));
           result2.setText(""+dd);
           result1.setText(""+finallbil);
       }
    }
}
        }
    };
    float finallbil;
    TextWatcher finalbillwatcher=new TextWatcher() {
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
    if (TextUtils.isEmpty(tips.getText().toString())) {
       float ddd=Float.parseFloat(koto_point.getText().toString());
       finallbil=ddd+((ddd*10)/100);
       float total=((ddd*10)/100);
        result3.setText(""+total);
        result1.setText(""+finallbil);
        result2.setText("0");
    }
    else if (!TextUtils.isEmpty(tips.getText().toString())){
        if (TextUtils.isEmpty(people.getText().toString())) {
            float percentage = Float.parseFloat(koto_point.getText().toString());
            float dec = percentage / 100;
            float total = dec * Float.parseFloat(tips.getText().toString().toString());
            finallbil=percentage+total;
            result3.setText(""+total);
            result1.setText(""+finallbil);
            result2.setText("0");
        }
        else if(!TextUtils.isEmpty(people.getText().toString())) {
            float percentage = Float.parseFloat(koto_point.getText().toString());
            float dec = percentage / 100;
            float total = dec * Float.parseFloat(tips.getText().toString().toString());
            finallbil=percentage+total;
            result3.setText(""+total);
            float dd=finallbil/(Float.parseFloat(people.getText().toString()));
            result2.setText(""+dd);
            result1.setText(""+finallbil);
        }
    }
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