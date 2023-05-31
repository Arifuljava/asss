package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UnitConverter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("ইউনিট কনভার্টার");
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
        LinearLayout allcontact=findViewById(R.id.allcontact);
        topAnimation = AnimationUtils.loadAnimation(UnitConverter.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(UnitConverter.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(UnitConverter.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(UnitConverter.this, R.anim.splash_end_animation);
        allcontact.setAnimation(endAnimation);
    }
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Calculatorhome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),Calculatorhome.class));
        return true;
    }

    public void age_calculator(View view) {
        startActivity(new Intent(getApplicationContext(),AgeCalculator.class));
    }

    public void scientific_cal(View view) {
        startActivity(new Intent(getApplicationContext(),Scientific_Calculator.class));
    }

    public void unitlength(View view) {
        startActivity(new Intent(getApplicationContext(),UnitLength.class));
    }

    public void unittemp(View view) {
        startActivity(new Intent(getApplicationContext(),UnitTemperature.class));
    }

    public void unitweight(View view) {
        startActivity(new Intent(getApplicationContext(),UnitWeight.class));
    }

    public void bmi(View view) {
        startActivity(new Intent(getApplicationContext(),MyBMI.class));
    }

    public void currencey(View view) {
        startActivity(new Intent(getApplicationContext(),CurrenceyConverter.class));
    }

    public void percentagess(View view) {
        startActivity(new Intent(getApplicationContext(),PercentageCalculator.class));
    }

    public void jewelerycal(View view) {
        startActivity(new Intent(getApplicationContext(),JuneLaryCalculatorDemo.class));
    }

    public void tippp(View view) {
        startActivity(new Intent(getApplicationContext(),TipCalculator.class));
    }

    public void speed(View view) {
        startActivity(new Intent(getApplicationContext(),SpeedCalculator.class));
    }

    public void unitpercentages(View view) {
        startActivity(new Intent(getApplicationContext(),AlgebraPercentages.class));
    }
    public void averages(View view) {
        startActivity(new Intent(getApplicationContext(),AlgebraAverage.class));
    }
    public void properation(View view) {
        startActivity(new Intent(getApplicationContext(),AljebraPoprtion.class));
    }
    public void ration(View view) {
        startActivity(new Intent(getApplicationContext(),AlgebraRatio.class));
    }
    public void lcmm(View view) {
        startActivity(new Intent(getApplicationContext(),AlgebraLCM.class));
    }
    public void prime(View view) {
        startActivity(new Intent(getApplicationContext(),AlgebraPrime.class));
    }
    public void speedio(View view) {
        startActivity(new Intent(getApplicationContext(),Unitacceleration.class));
    }

    public void areaaunit(View view) {
    }
    public void Cooking(View view) {
    }
    public void datastroage(View view) {
        startActivity(new Intent(getApplicationContext(), DataStroageActivity.class));
    }
    public void datatransfer(View view) {
        startActivity(new Intent(getApplicationContext(), DataTransferActivity.class));
    }
    public void energy(View view) {
        startActivity(new Intent(getApplicationContext(), EnergeyActivity.class));
    }
    public void force(View view) {
        startActivity(new Intent(getApplicationContext(), ForceActivity.class));
    }
    public void fuel(View view) {
        startActivity(new Intent(getApplicationContext(), FuelActivity.class));
    }
    public void lengthtt(View view) {
    }
    public void nbase(View view) {
        startActivity(new Intent(getApplicationContext(), NumericActivity.class));
    }
    public void power(View view) {
        startActivity(new Intent(getApplicationContext(), PowerActivity.class));
    }
    public void presure(View view) {
        startActivity(new Intent(getApplicationContext(), PresureActivity.class));
    }
    public void roman(View view) {
        startActivity(new Intent(getApplicationContext(), RomanNumberActivity.class));
    }
    public void speevvvvd(View view) {
        startActivity(new Intent(getApplicationContext(), SpeedCalculator.class));
    }
    public void temmp(View view) {
        startActivity(new Intent(getApplicationContext(), UnitTemperature.class));
    }
    public void tiiime(View view) {
        startActivity(new Intent(getApplicationContext(), TimeActivity.class));
    }
    public void torque(View view) {
        startActivity(new Intent(getApplicationContext(),TorqueActivity.class));
    }
    public void volumeggg(View view) {
        startActivity(new Intent(getApplicationContext(),VolumeActivity.class));
    }
    public void volumectic(View view) {
        startActivity(new Intent(getApplicationContext(),VolumetricActivity.class));
    }
    public void angle(View view) {
        startActivity(new Intent(getApplicationContext(),AngleCalcculator.class));
    }


    public void anglemeter(View view) {
        startActivity(new Intent(getApplicationContext(),WeightActivity.class));
    }
///finance sector

    public void financecurrencey(View view) {
        startActivity(new Intent(getApplicationContext(),CurrenceyConverter.class));
    }

    public void financeunit(View view) {
        startActivity(new Intent(getApplicationContext(),UnitPriceActivity.class));
    }

    public void financetax(View view) {
        startActivity(new Intent(getApplicationContext(),SalesTaxActivity.class));
    }


    public void financetip(View view) {
        startActivity(new Intent(getApplicationContext(),TipCalculator.class));
    }

    public void financeloan(View view) {
        startActivity(new Intent(getApplicationContext(),LoanActivity.class));
    }

    public void financeinterest(View view) {
        startActivity(new Intent(getApplicationContext(),InterestActivity.class));
    }
///healthindex

    public void healthbmi(View view) {
        startActivity(new Intent(getApplicationContext(),MyBMI.class));
    }

    public void healthfat(View view) {
        startActivity(new Intent(getApplicationContext(),HealthFat.class));
    }

    public void healthcaloric(View view) {
        startActivity(new Intent(getApplicationContext(),HealthCaloric.class));
    }
//time
    public void miliage(View view) {
        startActivity(new Intent(getApplicationContext(),AgeCalculator.class));
    }

    public void diffdate(View view) {
        startActivity(new Intent(getApplicationContext(),Differnecedate.class));
    }

    public void ohmslaw(View view) {
        startActivity(new Intent(getApplicationContext(),OthmsLaw.class));
    }

    public void milltime(View view) {
        startActivity(new Intent(getApplicationContext(),DifferenceTime.class));
    }
}