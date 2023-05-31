package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.dmoral.toasty.Toasty;

public class SimpleCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("ক্যালকুলেটর");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        //
        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        mylay=findViewById(R.id.mylay);
        result=findViewById(R.id.result);
    }
    EditText somitiname,sovapoti;
    LinearLayout mylay;
    TextView result;

    public void add(View view) {
        String first=somitiname.getText().toString();
        String second=sovapoti.getText().toString();
        if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
            Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
        }
        else {
            mylay.setVisibility(View.VISIBLE);
            double add=Double.parseDouble(first)+Double.parseDouble(second);
            result.setText("ফলাফল  : "+add);

        }
    }

    public void minus(View view) {
        String first=somitiname.getText().toString();
        String second=sovapoti.getText().toString();
        if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
            Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
        }
        else {
            mylay.setVisibility(View.VISIBLE);
            double add=Double.parseDouble(first)-Double.parseDouble(second);
            result.setText("ফলাফল  : "+add);

        }
    }

    public void mul(View view) {
        String first=somitiname.getText().toString();
        String second=sovapoti.getText().toString();
        if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
            Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
        }
        else {
            mylay.setVisibility(View.VISIBLE);
            double add=Double.parseDouble(first)*Double.parseDouble(second);
            result.setText("ফলাফল  : "+add);

        }
    }

    public void division(View view) {
        String first=somitiname.getText().toString();
        String second=sovapoti.getText().toString();
        if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
            Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
        }
        else {
            mylay.setVisibility(View.VISIBLE);
            float add=Float.parseFloat(first)/Float.parseFloat(second);
            result.setText("ফলাফল  : "+add);

        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Add_Kisti.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),Add_Kisti.class));
        return true;
    }
}