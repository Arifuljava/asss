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

import es.dmoral.toasty.Toasty;

public class MyBMI extends AppCompatActivity {
EditText weightEDTX,heightEDTX,heightEDTX1;
Button calculateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_b_m_i);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("BMI Calculator");
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
        weightEDTX=findViewById(R.id.weightEDTX);
        heightEDTX=findViewById(R.id.heightEDTX);
        heightEDTX1=findViewById(R.id.heightEDTX1);
        calculateBtn=findViewById(R.id.calculateBtn) ;
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight=weightEDTX.getText().toString();
                String height=heightEDTX.getText().toString();
                String height1=heightEDTX1.getText().toString();


                if (TextUtils.isEmpty(weight)) {
                    Toasty.error(getApplicationContext(),"Enter all values",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    if(!TextUtils.isEmpty(height)) {
                        if (Double.parseDouble(weight) > 0 && Double.parseDouble(weight) < 600 && (Double.parseDouble(height)/100) >= 0.50 && (Double.parseDouble(height)/100)< 2.50) {
                            Double height_value=Double.parseDouble(height)/100;
                            double value=Double.parseDouble(weight)/(height_value*height_value);
                            Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                            intent.putExtra("values",""+value);
                            startActivity(intent);

                        }
                        else { Toasty.error(getApplicationContext(),"Enter right information",Toasty.LENGTH_SHORT,true).show();
                            return;

                        }
                    }
                    else if (!TextUtils.isEmpty(height1)){
                        double mainwi=Double.parseDouble(height1)*2.54;
                        String second=""+mainwi;
                        if (Double.parseDouble(weight) > 0 && Double.parseDouble(weight) < 600 ) {
                            Double height_value=Double.parseDouble(second)/100;
                            double value=Double.parseDouble(weight)/(height_value*height_value);
                            Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                            intent.putExtra("values",""+value);
                            startActivity(intent);

                        }
                        else { Toasty.error(getApplicationContext(),"Enter right information",Toasty.LENGTH_SHORT,true).show();
                            return;

                        }
                    }
                }


            }
        });
    }

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