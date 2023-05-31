package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class HomeNewsPapers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_news_papers);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("News");
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
        startActivity(new Intent(getApplicationContext(),HomeMain.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeNewspaperList.class));
    }

    public void world(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeNewspaperList.class);
        intent.putExtra("name","আন্তর্জাতিক");

        startActivity(intent);
    }

    public void sports(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeNewspaperList.class);
        intent.putExtra("name","খেলাধুলা");

        startActivity(intent);
    }

    public void mazagine(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeNewspaperList.class);
        intent.putExtra("name","বিনোদন");

        startActivity(intent);
    }

    public void religion(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeNewspaperList.class);
        intent.putExtra("name","ধর্ম");

        startActivity(intent);
    }

    public void education(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeNewspaperList.class);
        intent.putExtra("name","শিক্ষা");

        startActivity(intent);
    }

    public void chakri(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeNewspaperList.class);
        intent.putExtra("name","চাকরি");

        startActivity(intent);
    }

}