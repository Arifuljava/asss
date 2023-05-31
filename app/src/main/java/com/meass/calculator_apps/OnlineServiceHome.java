package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class OnlineServiceHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_service_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Online Service");
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

    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
    }

    public void cash(View view) {
        Intent intent=new Intent(getApplicationContext(),OnlineServiceDetailsList.class);
        intent.putExtra("name","Job");

        startActivity(intent);
    }

    public void home(View view) {
        Intent intent=new Intent(getApplicationContext(),OnlineServiceDetailsList.class);
        intent.putExtra("name","Govt. Service");

        startActivity(intent);
    }

    public void calculator(View view) {
        Intent intent=new Intent(getApplicationContext(),OnlineServiceDetailsList.class);
        intent.putExtra("name","Passport");

        startActivity(intent);
    }

    public void due(View view) {
        Intent intent=new Intent(getApplicationContext(),OnlineServiceDetailsList.class);
        intent.putExtra("name","Shope");

        startActivity(intent);
    }
}