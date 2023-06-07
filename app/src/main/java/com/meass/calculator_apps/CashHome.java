package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class CashHome extends AppCompatActivity {
    Button taskCard6;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String chalan;
    String casssstoday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("ক্যাশ");
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
        ///
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        taskCard6=findViewById(R.id.taskCard6);
        firebaseFirestore.collection("Users")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("Coins")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                try {
                                    chalan= task.getResult().getString("coin");
                                    taskCard6.setText(chalan+"টাকা ");

                                    Calendar calender=Calendar.getInstance();
                                    int year = calender.get(Calendar.YEAR);
                                    int month = calender.get(Calendar.MONTH)+1;
                                    int day = calender.get(Calendar.DATE);
                                    String today = day+""+month+""+year;
                                    todaycash=findViewById(R.id.todaycash);
                                    previousbalance=findViewById(R.id.previousbalance);
                                    totalbaki=findViewById(R.id.totalbaki);
                                    intotalll=findViewById(R.id.intotalll);
                                    more=findViewById(R.id.more);
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
                                                                casssstoday = task.getResult().getString("coin");
                                                                todaycash.setText(casssstoday+"টাকা ");
                                                            }catch (Exception e) {
                                                                casssstoday = task.getResult().getString("coin");
                                                                todaycash.setText(casssstoday+"টাকা ");
                                                            }
                                                        }
                                                        else {
                                                            casssstoday = "0";
                                                            todaycash.setText(casssstoday+"টাকা ");
                                                        }
                                                    }
                                                    else {
                                                        casssstoday = "0";
                                                        todaycash.setText(casssstoday+"টাকা ");
                                                    }
                                                }
                                            });

                                    //total count
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
                                                                main_balance= task.getResult().getString("main_balance");
                                                                previousbalance.setText(main_balance+"টাকা ");
                                                                purches_balance= task.getResult().getString("purches_balance");
                                                                totalbaki.setText(purches_balance+"টাকা ");
                                                                double eee = (Double.parseDouble(casssstoday)+Double.parseDouble(main_balance))-Double.parseDouble(casssstoday);
                                                                intotalll.setText(""+eee+"টাকা ");
                                                                double ddddd = eee+Double.parseDouble(purches_balance);
                                                                double ddddddddd= Double.parseDouble(chalan)-ddddd;

                                                                more.setText(""+ddddddddd+"টাকা ");

                                                            }catch (Exception e) {
                                                                chalan= task.getResult().getString("coin");
                                                                taskCard6.setText(chalan+"টাকা ");
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                }catch (Exception e) {
                                    chalan= task.getResult().getString("coin");
                                    taskCard6.setText(chalan+"টাকা ");
                                }
                            }
                        }
                    }
                });



    }
    String main_balance,purches_balance;

    Button todaycash,previousbalance,totalbaki,intotalll,more;

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
    }

    public void addtocash(View view) {
        startActivity(new Intent(getApplicationContext(),AddToCash.class));
    }

    public void history(View view) {
        startActivity(new Intent(getApplicationContext(),DailyCash.class));
    }

    public void world(View view) {
        startActivity(new Intent(getApplicationContext(),CashList.class));
    }

    public void addchalan(View view) {
        final Dialog mDialog = new Dialog(CashHome.this);


        //mDialog = new Dialog(HomeACTIVITY.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //LayoutInflater factory = LayoutInflater.from(this);

        mDialog.setContentView(R.layout.chalan);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FloatingActionButton dialogClose=(FloatingActionButton)mDialog.findViewById(R.id.dialogClose);
        EditText methodename=(EditText)mDialog.findViewById(R.id.methodename);
        EditText minwith=(EditText)mDialog.findViewById(R.id.minwith);
        TextView resulttt=(TextView) mDialog.findViewById(R.id.resulttt);
        Button login_button=(Button)mDialog.findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first=methodename.getText().toString();
                String second=minwith.getText().toString();
                if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
                    Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
                }
                else {
                    resulttt.setVisibility(View.GONE);
                    firebaseFirestore.collection("Users")
                            .document(firebaseAuth.getCurrentUser().getUid())
                            .collection("Coins")
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().exists()) {
                                            try {
                                               double lll  = Double.parseDouble(task.getResult().getString("coin"))+ Double.parseDouble(second) ;
                                                firebaseFirestore.collection("Users")
                                                        .document(firebaseAuth.getCurrentUser().getUid())
                                                        .collection("Coins")
                                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                                        .update("coin",""+lll)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    taskCard6.setText(lll+"টাকা ");
                                                                    mDialog.dismiss();
                                                                    Toast.makeText(CashHome.this, "সংরক্ষণ সফলভাবে সম্পন্ন হয়েছে", Toast.LENGTH_SHORT).show();
                                                                    startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
                                                                }
                                                            }
                                                        });

                                            }catch (Exception e) {
                                                chalan= task.getResult().getString("coin");
                                                taskCard6.setText(chalan+"টাকা ");
                                            }
                                        }
                                    }
                                }
                            });


                }
            }
        });
        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();

            }
        });

        mDialog.create();
        mDialog.show();
    }
}