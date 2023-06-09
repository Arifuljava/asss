package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class AddToCash extends AppCompatActivity {
    EditText bikkridan,kena_dam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cash);
        bikkridan=findViewById(R.id.bikkridan);
        kena_dam=findViewById(R.id.kena_dam);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("অ্যাড ক্যাশ ");
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

        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        sovapoti_english=findViewById(R.id.sovapoti_english);
        fatherba=findViewById(R.id.fatherba);
        fatheren=findViewById(R.id.fatheren);
        mother=findViewById(R.id.mother);
        p_address=findViewById(R.id.p_address); 

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Calendar calender=Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH)+1;
        int day = calender.get(Calendar.DATE);
       // Toast.makeText(this, ""+day, Toast.LENGTH_SHORT).show();
        Button cirLoginButton=findViewById(R.id.cirLoginButton);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(kena_dam.getText().toString())|| TextUtils.isEmpty(bikkridan.getText().toString())||Double.parseDouble(sovapoti.getText().toString())<11||TextUtils.isEmpty(p_address.getText().toString())||TextUtils.isEmpty(somitiname.getText().toString())||TextUtils.isEmpty(sovapoti.getText().toString())||
                        TextUtils.isEmpty(sovapoti_english.getText().toString())||TextUtils.isEmpty(fatherba.getText().toString())||
                        TextUtils.isEmpty(fatheren.getText().toString())||TextUtils.isEmpty(mother.getText().toString())) {
                    Toast.makeText(AddToCash.this, "সমস্ত তথ্য লিখুন", Toast.LENGTH_SHORT).show();
                }
                else {
                    long ts= System.currentTimeMillis()/1000; 
                    String tt= ""+ts ; 
                    AddCashmodel cashmodel=new AddCashmodel(somitiname.getText().toString(),sovapoti.getText().toString(),sovapoti_english.getText().toString(),fatherba.getText().toString()
                    ,fatheren.getText().toString(),mother.getText().toString(),p_address.getText().toString(),tt,p_address.getText().toString(),kena_dam.getText().toString(),bikkridan.getText().toString());

                    Calendar calender=Calendar.getInstance();
                    int year = calender.get(Calendar.YEAR);
                    int month = calender.get(Calendar.MONTH)+1;
                    int day = calender.get(Calendar.DATE);
                    String today = day+""+month+""+year;

                    firebaseFirestore.collection("DailyCash_Daily")
                            .document(firebaseAuth.getCurrentUser().getUid())
                            .collection(""+today)
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().exists()) {
                                            try {
                                               String casssstoday = task.getResult().getString("coin");
                                                double ttt = Double.parseDouble(sovapoti_english.getText().toString())+Double.parseDouble(casssstoday);
                                                String baki=task.getResult().getString("baki");
                                                double dbkai= Double.parseDouble(baki)+Double.parseDouble(p_address.getText().toString());
                                                firebaseFirestore.collection("DailyCash_Daily_List")
                                                        .document(firebaseAuth.getCurrentUser().getUid())
                                                        .collection(""+today)
                                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                                        .collection("List").document(""+tt)
                                                        .set(cashmodel)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                            }
                                                        });


                                                firebaseFirestore.collection("DailyCash_Daily")
                                                        .document(firebaseAuth.getCurrentUser().getUid())
                                                        .collection(""+today)
                                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                                        .update("coin",""+ttt,"baki",""+dbkai)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                            }
                                                        });

                                            }catch (Exception e) {

                                            }
                                        }
                                        else {


                                            double ttt = Double.parseDouble(sovapoti_english.getText().toString());
                                            Map<String, Object> user = new HashMap<>();
                                            user.put("coin", ""+ttt);
                                            user.put("baki",""+p_address.getText().toString());
                                            user.put("jomadaily","0");

                                            firebaseFirestore.collection("DailyCash_Daily")
                                                    .document(firebaseAuth.getCurrentUser().getUid())
                                                    .collection(""+today)
                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                    .set(user)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                        }
                                                    });
                                            firebaseFirestore.collection("DailyCash_Daily_List")
                                                    .document(firebaseAuth.getCurrentUser().getUid())
                                                    .collection(""+today)
                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                    .collection("List").document(""+tt)
                                                    .set(cashmodel)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                        }
                                                    });
                                        }
                                    }
                                    else {

                                        double ttt = Double.parseDouble(sovapoti_english.getText().toString());
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("coin", ""+ttt);
                                        user.put("baki",""+p_address.getText().toString());
                                        user.put("jomadaily","0");

                                        firebaseFirestore.collection("DailyCash_Daily")
                                                .document(firebaseAuth.getCurrentUser().getUid())
                                                .collection(""+today)
                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                .set(user)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                        firebaseFirestore.collection("DailyCash_Daily_List")
                                                .document(firebaseAuth.getCurrentUser().getUid())
                                                .collection(""+today)
                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                .collection("List").document(""+tt)
                                                .set(cashmodel)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });

                                    }
                                }
                            });

                    firebaseFirestore.collection("Users")
                            .document(firebaseAuth.getCurrentUser().getUid())
                            .collection("Main_Balance")
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().exists()) {
                                            try {
                                               String main_balance= task.getResult().getString("main_balance");

                                              String  purches_balance= task.getResult().getString("purches_balance");

                                                double eee = Double.parseDouble(sovapoti_english.getText().toString())+Double.parseDouble(main_balance);

                                                double ddddd = eee+Double.parseDouble(purches_balance)+Double.parseDouble(p_address.getText().toString());

                                                firebaseFirestore.collection("Users")
                                                        .document(firebaseAuth.getCurrentUser().getUid())
                                                        .collection("Main_Balance")
                                                        .document(firebaseAuth.getCurrentUser().getEmail()).update("main_balance",""+eee,"purches_balance",""+ddddd)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                            }
                                                        });

                                            }catch (Exception e) {

                                            }
                                        }
                                    }
                                }
                            });

                    firebaseFirestore.collection("AddToCash")
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .collection("List")
                            .document(tt)
                            .set(cashmodel)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddToCash.this, "সম্পন্ন", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),CashHome.class));
                                    }
                                }
                            });
                }

            }
        });
        Button personaluser=findViewById(R.id.personaluser);
        personaluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FlatDialog flatDialog1 = new FlatDialog(AddToCash.this);
                flatDialog1.setTitle("ব্যক্তিগত খরচ")
                        .setSubtitle("ব্যক্তিগত খরচের বিবরণ যোগ করুন")
                        .setFirstTextFieldHint("বিবরণ")
                        .setSecondTextFieldHint("পরিমাণ")
                        .setFirstButtonText("সংরক্ষণ")
                        .setSecondButtonText("বাতিল করুন")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog1.dismiss();
                                String one =flatDialog1.getFirstTextField().toString(); 
                                String two = flatDialog1.getSecondTextField().toLowerCase(); 
                                if (TextUtils.isEmpty(one)||TextUtils.isEmpty(two)) {
                                    Toast.makeText(AddToCash.this, "সমস্ত তথ্য লিখুন", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    firebaseFirestore.collection("Users")
                                            .document(firebaseAuth.getCurrentUser().getUid())
                                            .collection("Main_Balance")
                                            .document(firebaseAuth.getCurrentUser().getEmail())
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (task.getResult().exists()) {
                                                            Calendar calender=Calendar.getInstance();
                                                            int year = calender.get(Calendar.YEAR);
                                                            int month = calender.get(Calendar.MONTH)+1;
                                                            int day = calender.get(Calendar.DATE);
                                                            String today = day+""+month+""+year;

                                                            firebaseFirestore.collection("DailyCash_Daily")
                                                                    .document(firebaseAuth.getCurrentUser().getUid())
                                                                    .collection(""+today)
                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                            if (task.isSuccessful()) {
                                                                                if (task.getResult().exists()) {
                                                                                    double ddd = Double.parseDouble(task.getResult().getString("jomadaily"))+Double.parseDouble(two);
                                                                                    firebaseFirestore.collection("DailyCash_Daily")
                                                                                            .document(firebaseAuth.getCurrentUser().getUid())
                                                                                            .collection(""+today)
                                                                                            .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                            .update("jomadaily",""+ddd)
                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                @Override
                                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                                }
                                                                                            });
                                                                                }
                                                                                else {
                                                                                    Map<String, Object> user = new HashMap<>();
                                                                                    user.put("coin", "0");
                                                                                    user.put("baki","0");
                                                                                    user.put("jomadaily",""+two);

                                                                                    firebaseFirestore.collection("DailyCash_Daily")
                                                                                            .document(firebaseAuth.getCurrentUser().getUid())
                                                                                            .collection(""+today)
                                                                                            .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                            .set(user)
                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                @Override
                                                                                                public void onComplete(@NonNull Task<Void> task) {

                                                                                                }
                                                                                            });
                                                                                }

                                                                            }

                                                                        }
                                                                    });
                                                            String cashwalet=task.getResult().getString("cashwalet");
                                                            double dd = Double.parseDouble(cashwalet)+Double.parseDouble(two);
                                                            firebaseFirestore.collection("Users")
                                                                    .document(firebaseAuth.getCurrentUser().getUid())
                                                                    .collection("Main_Balance")
                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                    .update("cashwalet",""+dd)
                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isSuccessful()) {
                                                                                Toast.makeText(AddToCash.this, "সফলভাবে সমস্ত তথ্য সংরক্ষণ করুন", Toast.LENGTH_SHORT).show();
                                                                            }

                                                                        }
                                                                    });
                                                        }

                                                    }

                                                }
                                            });

                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog1.dismiss();
                            }
                        })
                        .show();
            }
        });


    }
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    EditText somitiname,mother,fatheren,fatherba,sovapoti_english,sovapoti,p_address;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),CashHome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),CashHome.class));
        return true;
    }
}