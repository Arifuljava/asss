package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import es.dmoral.toasty.Toasty;

public class JuelMinus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juel_minus);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("গহনার ওজন বিয়োগ");
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
        //first
        kotovori=findViewById(R.id.kotovori);
        koto_ana=findViewById(R.id.koto_ana);
        koto_roti=findViewById(R.id.koto_roti);
        koto_point=findViewById(R.id.koto_point);
        //second
        kotovori1=findViewById(R.id.kotovori1);
        koto_ana1=findViewById(R.id.koto_ana1);
        koto_roti1=findViewById(R.id.koto_roti1);
        koto_point1=findViewById(R.id.koto_point1);
        detailss=findViewById(R.id.detailss);
        relationlayout=findViewById(R.id.relationlayout);
        ////data
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        Button cirLoginButton=findViewById(R.id.cirLoginButton);
        cirLoginButton=findViewById(R.id.cirLoginButton);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(kotovori.getText().toString())||TextUtils.isEmpty(koto_ana.getText().toString())||
                        TextUtils.isEmpty(koto_roti.getText().toString())||TextUtils.isEmpty(koto_point.getText().toString())
                        || TextUtils.isEmpty(kotovori1.getText().toString())||TextUtils.isEmpty(koto_ana1.getText().toString())||
                        TextUtils.isEmpty(koto_roti1.getText().toString())||TextUtils.isEmpty(koto_point1.getText().toString()))
                {
                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    String first=kotovori.getText().toString()+""+koto_ana.getText().toString()+""+koto_roti.getText().toString()
                            +""+koto_point.getText().toString();
                    String second=kotovori1.getText().toString()+""+koto_ana1.getText().toString()+""+koto_roti1.getText().toString()
                            +""+koto_point1.getText().toString();
                    int one=Integer.parseInt(first)-Integer.parseInt(second);
                    if (Double.parseDouble(first)<Double.parseDouble(second)) {
                        Toasty.error(getApplicationContext(),"বৈধ তথ্য  লিখুন",Toasty.LENGTH_SHORT,true).show();
                        return;
                    }
                    else {
                        int vori=one/1000;
                        int    two=one%1000;
                        int ana=two/100;
                        int three=two%100;
                        int roti=three/10;
                        int four=three%10;
                        relationlayout.setVisibility(View.VISIBLE);
                        detailss.setText(" ভরি : "+vori+" \n" +
                                " আনা  : "+ana+"  \n" +
                                " রতি  : "+roti+"  \n" +
                                "পয়েন্ট  : "+four+" ");
                    }





                }
            }
        });

    }
    LinearLayout relationlayout;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    EditText mottaka,kotovori,koto_ana,koto_roti,koto_point,kotovori1,detailss,koto_point1,koto_roti1,koto_ana1;
    public void delte(View view) {
    }

    public void saves(View view) {
    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),JuneLaryCalculatorDemo.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),JuneLaryCalculatorDemo.class));
    }
}