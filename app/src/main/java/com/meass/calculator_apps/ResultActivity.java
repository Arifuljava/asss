package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
LinearLayout containerL;
String double_valuye,textvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        try {
            values=getIntent().getStringExtra("values");
            double mainnnn=Double.parseDouble(values);
             double_valuye=String.format("%.0f",mainnnn);
            textvalue=String.format("%.1f",mainnnn);
        }catch (Exception e) {
            values=getIntent().getStringExtra("values");
            double mainnnn=Double.parseDouble(values);
            double_valuye=String.format("%.1f",mainnnn);
            textvalue=String.format("%.1f",mainnnn);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("BMI Result");
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
        containerL=findViewById(R.id.containerL);
        skipResultBTN=findViewById(R.id.skipResultBTN);
        skipResultBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyBMI.class));
            }
        });
        bmiValueTV=findViewById(R.id.bmiValueTV);
        bmiFlagImgView=findViewById(R.id.bmiFlagImgView);
        bmiLabelTV=findViewById(R.id.bmiLabelTV);
        commentTV=findViewById(R.id.commentTV);
        advice1TV=findViewById(R.id.advice1TV);
        advice2TV=findViewById(R.id.advice2TV);
        advice3TV=findViewById(R.id.advice3TV);
        advice1IMG=findViewById(R.id.advice1IMG);
        advice2IMG=findViewById(R.id.advice2IMG);
        advice3IMG=findViewById(R.id.advice3IMG);
        bmiValueTV.setText(""+double_valuye);

        if (Double.parseDouble(double_valuye)<18.5) {
            containerL.setBackgroundResource(R.color.colorYellow);
            bmiFlagImgView.setImageResource(R.drawable.ex);
            bmiLabelTV.setText("You have an UnderWeight!");
            commentTV.setText("Here are some advices To help you increase your weight");
            advice1IMG.setImageResource(R.drawable.nowater);
            advice1TV.setText("Don't drink water before meals");
            advice2IMG.setImageResource(R.drawable.bigmeal);
            advice2TV.setText("Use bigger plates");
            advice3TV.setText("Get quality sleep");
        }
        else  if (Double.parseDouble(double_valuye)>25) {
            containerL.setBackgroundResource(R.color.colorRed);
            bmiFlagImgView.setImageResource(R.drawable.warning);
            bmiLabelTV.setText("You have an OverWeight!");
            commentTV.setText("Here are some advices To help you decrease your weight");
            advice1IMG.setImageResource(R.drawable.water);
            advice1TV.setText("Drink water a half hour before meals");
            advice2IMG.setImageResource(R.drawable.twoeggs);
            advice2TV.setText("Eeat only two meals per day and make sure that they contains a high protein");
            advice3TV.setText("Drink coffee or tea and Avoid sugary food");
            advice3IMG.setImageResource(R.drawable.nosugar);
        }




    }
    String values;
    ImageView skipResultBTN,bmiFlagImgView,advice1IMG,advice2IMG,advice3IMG;
    TextView bmiValueTV,bmiLabelTV,commentTV,advice1TV,advice2TV,advice3TV;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MyBMI.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),MyBMI.class));
        return true;
    }
}