package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class AddCallActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("কল  যোগ করুন");
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
        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        sovapoti_english=findViewById(R.id.sovapoti_english);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        datetime=findViewById(R.id.datetime);
        try {
            detctor=getIntent().getStringExtra("detctor");
            name=getIntent().getStringExtra("name");
            number=getIntent().getStringExtra("number");
            if (detctor.equals("2")) {
            }
            else {
                somitiname.setText(name);
                sovapoti.setText(number);
            }
        }catch (Exception e) {
        }


    }
    String detctor,name,number;
    EditText somitiname,sovapoti,sovapoti_english,datetime;
    public void add_balance(View view) {

    }
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),AddCallListActivity.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),AddCallListActivity.class));
    }

    public void barcode(View view) {
        if (TextUtils.isEmpty(somitiname.getText().toString())||
                TextUtils.isEmpty(sovapoti.getText().toString())||
                TextUtils.isEmpty(sovapoti_english.getText().toString())||
        TextUtils.isEmpty(datetime.getText().toString())) {
            Toast.makeText(this, "সমস্ত তথ্য লিখুন", Toast.LENGTH_SHORT).show();
        }
        else {
            long ts= System.currentTimeMillis()/1000;
            String tss= ""+ts;
            String uuid = UUID.randomUUID().toString();
            ContactModel  contactModel = new  ContactModel(somitiname.getText().toString(),sovapoti.getText().toString(),sovapoti_english.getText().toString(),firebaseAuth.getCurrentUser().getEmail(),
                    tss, datetime.getText().toString());
            firebaseFirestore.collection("CallList")
                    .document(firebaseAuth.getCurrentUser().getEmail())
                    .collection("List")
                    .document(tss)
                    .set(contactModel)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddCallActivity.this, "সম্পন্ন", Toast.LENGTH_SHORT).show();
                                somitiname.setText("");
                                sovapoti.setText("");
                                sovapoti_english.setText("");
                                datetime.setText("");
                            }
                        }
                    });
        }
    }


}