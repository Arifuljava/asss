package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import es.dmoral.toasty.Toasty;

public class JewrlcateryCal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewrlcatery_cal);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle(" টাকার হিসাব");
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
        ////
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        //edittext
        mottaka=findViewById(R.id.mottaka);
        kotovori=findViewById(R.id.kotovori);
        koto_ana=findViewById(R.id.koto_ana);
        koto_roti=findViewById(R.id.koto_roti);
        koto_point=findViewById(R.id.koto_point);
        /////
        cirLoginButton=findViewById(R.id.cirLoginButton);
        relationlayout=findViewById(R.id.relationlayout);
        finaltakaka=findViewById(R.id.finaltakaka);
        detailss=findViewById(R.id.detailss);
        ///cir
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mottaka.getText().toString())||TextUtils.isEmpty(kotovori.getText().toString())||TextUtils.isEmpty(koto_ana.getText().toString())||
                        TextUtils.isEmpty(koto_roti.getText().toString())||TextUtils.isEmpty(koto_point.getText().toString())) {
                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    double takka=voriprice+motanaprice+rotiprice+pointprice;
                    finaltakaka.setText("মোট টাকা : "+takka);

                }

            }
        });


        koto_ana.addTextChangedListener(anawatcher);
        kotovori.addTextChangedListener(voriwatcher);
        koto_roti.addTextChangedListener(rotiwatcher);
        koto_point.addTextChangedListener(pintwatcher);


    }
    double pointprice;
    TextWatcher pintwatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String maincheck=s.toString();
            if (TextUtils.isEmpty(maincheck)) {
            }
            else {
                if(TextUtils.isEmpty(mottaka.getText().toString())) {
                }
                else {
                   double every=(Double.parseDouble(mottaka.getText().toString())/96);
                   pointprice=(every/10)*Double.parseDouble(maincheck);
                   relationlayout.setVisibility(View.VISIBLE);


                }
            }
        }
    };
    double rotiprice;
    TextWatcher rotiwatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
           String maincheck=s.toString();
            if (TextUtils.isEmpty(maincheck)) {
            }
            else {
                if(TextUtils.isEmpty(mottaka.getText().toString())) {
                }
                else {
                    rotiprice=(Double.parseDouble(mottaka.getText().toString())/96)*Double.parseDouble(maincheck);

                }
            }
        }
    };
    double voriprice;
    TextWatcher voriwatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
String maincheck=s.toString();
if (TextUtils.isEmpty(maincheck)) {
}
else {
    voriprice=(Double.parseDouble(mottaka.getText().toString()))*Double.parseDouble(maincheck);
    ///details

    double vori=Double.parseDouble(mottaka.getText().toString());
    double ana=vori/16;
    double roti=vori/96;
    double point=roti/10;
    detailss.setText("১ ভরি : "+vori+" টাকা\n" +
            "১ আনা  : "+ana+" টাকা \n" +
            "১ রতি  : "+roti+" টাকা \n" +
            "১ পয়েন্ট  : "+point+" টাকা");
}
        }
    };
    TextWatcher anawatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            check=s.toString();
            if (TextUtils.isEmpty(check)) {
            }
            else {
                if(TextUtils.isEmpty(mottaka.getText().toString())) {
                }
                else {
                    motanaprice=(Double.parseDouble(mottaka.getText().toString())/16)*Double.parseDouble(check);

                }
            }

        }
    };
    double motanaprice;

    String check;
    LinearLayout relationlayout;
    Button cirLoginButton;
    EditText mottaka,kotovori,koto_ana,koto_roti,koto_point,finaltakaka,detailss;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),JuneLaryCalculatorDemo.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),JuneLaryCalculatorDemo.class));
    }

    public void delte(View view) {
    }

    public void saves(View view) {
    }
}