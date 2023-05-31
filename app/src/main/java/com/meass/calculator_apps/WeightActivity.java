package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

public class WeightActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
        EditText Email_Log;
        String valueFromSpinner;
        String valueFromSpinner1;
        String valueFromSpinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Weight");
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

        //  koto_point.addTextChangedListener(first);
        // people.addTextChangedListener(second);
        koto_point1=findViewById(R.id.koto_point1);
        people1=findViewById(R.id.people1);
        tips1=findViewById(R.id.tips1);
        result11=findViewById(R.id.result11);
        spinnerTextSize = findViewById(R.id.spinner1);
        spinnerTextSize1 = findViewById(R.id.spinner2);
        spinnerTextSize.setOnItemSelectedListener(this);
        spinnerTextSize1.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.wight);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.drop, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter);
        String[] textSizes11 = getResources().getStringArray(R.array.wight);

        ArrayAdapter adapter1 = new ArrayAdapter(this,
                R.layout.drop, textSizes11);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize1.setAdapter(adapter1);





    }
    TextWatcher forth=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check11=s.toString();
            if (TextUtils.isEmpty(check11)) {

            }
            else {

                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();
                float kpr= (float) (Float.parseFloat(result11.getText().toString())*(453.59));
                koto_point1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(result11.getText().toString())*(0.45));
                people1.setText(""+fps);
                float mph= (float) (Float.parseFloat(result11.getText().toString())*(0.000454));
                tips1.setText(""+mph);

            }
        }
    };
    TextWatcher third=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check11=s.toString();
            if (TextUtils.isEmpty(check11)) {

            }
            else {

                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();
                float kpr= (float) (Float.parseFloat(tips1.getText().toString())*(1000000));
                koto_point1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(tips1.getText().toString())*(1000));
                people1.setText(""+fps);
                float mph= (float) (Float.parseFloat(tips1.getText().toString())*(2204.62));
                result11.setText(""+mph);

            }
        }
    };
    int  lastTouchAction ;
    TextWatcher second=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String check11=s.toString();
            if (TextUtils.isEmpty(check11)) {

            }
            else {
                // Toast.makeText(Unitacceleration.this, ""+check11, Toast.LENGTH_SHORT).show();
                float kpr= (float) (Float.parseFloat(people1.getText().toString())*(1000));
                koto_point1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(people1.getText().toString())*(0.001));
                tips1.setText(""+fps);
                float mph= (float) (Float.parseFloat(people1.getText().toString())*(2.2));
                result11.setText(""+mph);

            }
        }
    };
    TextWatcher first=new TextWatcher() {
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
                float kpr= (float) (Float.parseFloat(koto_point1.getText().toString())*(0.001));
                people1.setText(""+kpr);
                // float mps=Float.parseFloat(koto_point.getText().toString())*Float.parseFloat("0.277778");
                float fps= (float) (Float.parseFloat(koto_point1.getText().toString())*(0.000001));
                tips1.setText(""+fps);
                float mph= (float) (Float.parseFloat(koto_point1.getText().toString())*(0.002205));
                result11.setText(""+mph);
            }
        }
    };
    EditText koto_point1,result11,tips1,people1;

    EditText koto_point,people,tips,result1,result2,result3;
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner1) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            tips1.setText(valueFromSpinner);
            Toast.makeText(this, ""+koto_point1.getText().toString(), Toast.LENGTH_SHORT).show();

            if(!TextUtils.isEmpty(koto_point1.getText().toString())) {
                Toast.makeText(this, ""+koto_point1.getText().toString(), Toast.LENGTH_SHORT).show();


                    if (tips1.getText().toString().toLowerCase().equals("gram")) {
                        if (result11.getText().toString().toLowerCase().contains("gram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*1;
                            people1.setText(""+finalvalue);
                            Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*0.001;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*0.000001;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*0.002205;
                            people1.setText(""+finalvalue);
                        }
                    }
                    ////kilogram
                    else  if (valueFromSpinner.toLowerCase().equals("kilogram")) {
                        if (valueFromSpinner1.toLowerCase().equals("gram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*1000;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*1;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*0.001;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*2.2;
                            people1.setText(""+finalvalue);
                        }
                    }
                    ////mt
                    else  if (valueFromSpinner.toLowerCase().equals("metric ton")) {
                        if (valueFromSpinner1.toLowerCase().equals("gram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*1000000;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*1000;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*1;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*2204.62;
                            people1.setText(""+finalvalue);
                        }
                    }
                    ///
                    else  if (valueFromSpinner.toLowerCase().equals("pound")) {
                        if (valueFromSpinner1.toLowerCase().equals("gram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*453.59;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*0.45;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*0.000454;
                            people1.setText(""+finalvalue);
                        }
                        else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                            String get=koto_point1.getText().toString();
                            double finalvalue=Double.parseDouble(get)*1;
                            people1.setText(""+finalvalue);
                        }

                }


            }
            else {
            }






        }
         if (parent.getId() == R.id.spinner2) {
            valueFromSpinner1 = parent.getItemAtPosition(position).toString();
             result11.setText(valueFromSpinner1);

            /*
            if (!TextUtils.isEmpty(people1.getText().toString())) {
                if (valueFromSpinner.toLowerCase().equals("gram")) {
                    if (valueFromSpinner1.toLowerCase().equals("gram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*1;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*0.001;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*0.000001;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*0.002205;
                        koto_point1.setText(""+finalvalue);
                    }
                }
                ////kilogram
                else  if (valueFromSpinner.toLowerCase().equals("kilogram")) {
                    if (valueFromSpinner1.toLowerCase().equals("gram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*1000;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*1;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*0.001;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*2.2;
                        koto_point1.setText(""+finalvalue);
                    }
                }
                ////mt
                else  if (valueFromSpinner.toLowerCase().equals("metric ton")) {
                    if (valueFromSpinner1.toLowerCase().equals("gram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*1000000;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*1000;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*1;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*2204.62;
                        koto_point1.setText(""+finalvalue);
                    }
                }
                ///
                else  if (valueFromSpinner.toLowerCase().equals("pound")) {
                    if (valueFromSpinner1.toLowerCase().equals("gram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*453.59;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("kilogram")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*0.45;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("metric ton")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*0.000454;
                        koto_point1.setText(""+finalvalue);
                    }
                    else  if (valueFromSpinner1.toLowerCase().equals("pound")) {
                        String get=people1.getText().toString();
                        double finalvalue=Double.parseDouble(get)*1;
                        koto_point1.setText(""+finalvalue);
                    }
                }
            }
             */


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
