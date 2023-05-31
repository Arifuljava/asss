package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import es.dmoral.toasty.Toasty;

public class AljebraPoprtion extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
    EditText Email_Log;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView no_of_items, total_amount, spinner4;
    String package_name, package_price, packing;
    EditText spinner1, spinner2;
    Button upgrade;
    KProgressHUD kProgressHUD;
    private ClipboardManager myClipboard;
    private ClipData myClip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aljebra_poprtion);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Proprtion");
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
        ///find
        hintss=findViewById(R.id.hintss);
        koto_point=findViewById(R.id.koto_point);
        people=findViewById(R.id.people);
        result1=findViewById(R.id.result1);
        resulty=findViewById(R.id.resulty);


        spinnerTextSize = findViewById(R.id.spinner3);
        spinnerTextSize.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.avgprop);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.algeb, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter);
        resultry=findViewById(R.id.resultry);

    }

    EditText koto_point,people,result1,resulty,resultry;
    TextView hintss;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner3) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.equals("Direct Proportional")) {
                //hintss.setText("A - B% = X");
                if (TextUtils.isEmpty(koto_point.getText().toString())||TextUtils.isEmpty(people.getText().toString())|| TextUtils.isEmpty(resulty.getText().toString())) {
                    Toasty.info(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
float x=Float.parseFloat(people.getText().toString())*Float.parseFloat(resulty.getText().toString());
float main=x/Float.parseFloat(koto_point.getText().toString());
resultry.setText("Y = "+main);



                }

            }
            else  if (valueFromSpinner.equals("Indirectly Proportional")) {
                // hintss.setText("A + B% = X");
                float x=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat(resulty.getText().toString());
                float main=x/Float.parseFloat(people.getText().toString());
                resultry.setText("Y = "+main);

            }


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}