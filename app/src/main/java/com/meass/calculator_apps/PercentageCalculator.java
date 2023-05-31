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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class PercentageCalculator extends AppCompatActivity {
EditText somitiname,sovapoti,calculation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percentage_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Percentage Calculator");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        calculation=findViewById(R.id.calculation);
        sovapoti.addTextChangedListener(textWatcher);
        topAnimation = AnimationUtils.loadAnimation(PercentageCalculator.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(PercentageCalculator.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(PercentageCalculator.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(PercentageCalculator.this, R.anim.splash_end_animation);
        somitiname.setAnimation(topAnimation);
        sovapoti.setAnimation(startAnimation);
        calculation.setAnimation(endAnimation);
    }
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String  check;
    TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String first=somitiname.getText().toString();
            String second=s.toString();
if (TextUtils.isEmpty(first)) {
    Toasty.info(getApplicationContext(),"Enter first number",Toasty.LENGTH_SHORT,true).show();
    return;
}
else if (!TextUtils.isEmpty(first)) {
   if (TextUtils.isEmpty(second)) {
       calculation.setVisibility(View.INVISIBLE);
   }
   else {

       float percentage = Float.parseFloat(second.toString());
       float dec = percentage / 100;
       float total = dec * Float.parseFloat(first.toString());
       calculation.setVisibility(View.VISIBLE);

       float sub=Float.parseFloat(first.toString())- total;
       calculation.setText("Main Value : "+first+"\n" +
               "Percentage Value : "+total+"\n" +
               "Main value after percentage : "+sub);

       /*
       Toast.makeText(PercentageCalculator.this, first+""+second, Toast.LENGTH_SHORT).show();
       double dd=(Double.parseDouble(second)*100)/Double.parseDouble(first);
       calculation.setVisibility(View.VISIBLE);

       String double_valuye=String.format("%.2f",dd);
       double ddd=Double.parseDouble(first)-dd;
       String double_valuye1=String.format("%.2f",ddd);
       calculation.setText("Main Value : "+first+"\n" +
               "Percentage Value : "+double_valuye+"\n" +
               "Main value after percentage : "+double_valuye1);
        */
   }

}
else if(TextUtils.isEmpty(second)) {
}
        }
    };
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
        return true;
    }
}