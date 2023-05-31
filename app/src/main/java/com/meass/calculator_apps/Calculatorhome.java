package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Calculatorhome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatorhome);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("ক্যালকুলেটর");
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
        CardView dailyCheckCard=findViewById(R.id.dailyCheckCard);
        CardView luckySpinCard=findViewById(R.id.luckySpinCard);
        CardView taskCard=findViewById(R.id.taskCard);

        topAnimation = AnimationUtils.loadAnimation(Calculatorhome.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(Calculatorhome.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(Calculatorhome.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(Calculatorhome.this, R.anim.splash_end_animation);
        dailyCheckCard.setAnimation(topAnimation);
        luckySpinCard.setAnimation(bottomAnimation); taskCard.setAnimation(startAnimation);

    }
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
    }

    public void unitconverter(View view) {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }

    public void sports(View view) {
    }

    public void world(View view) {
    }
}