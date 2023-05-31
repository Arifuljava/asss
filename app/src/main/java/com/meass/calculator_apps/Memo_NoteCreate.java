package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.time.LocalDate;
import java.time.ZoneId;

import es.dmoral.toasty.Toasty;

public class Memo_NoteCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo__note_create);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("নোট");
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
        ///
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        //edittext
        somitiname=findViewById(R.id.somitiname);
        fatherba=findViewById(R.id.fatherba);
        mother=findViewById(R.id.mother);
        p_address=findViewById(R.id.p_address);
        votar=findViewById(R.id.votar);

        dailyCheckCard=findViewById(R.id.dailyCheckCard);
        dailyCheckCard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(somitiname.getText().toString())|| TextUtils.isEmpty(fatherba.getText().toString())
                ||TextUtils.isEmpty(mother.getText().toString())||TextUtils.isEmpty(p_address.getText().toString())
                ||TextUtils.isEmpty(votar.getText().toString())) {
                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    final KProgressHUD progressDialog=  KProgressHUD.create(Memo_NoteCreate.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setLabel("তথ্য আপলোড করা হচ্ছে.....")
                            .setCancellable(false)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();
                    ZoneId z = ZoneId.of( "America/Montreal" );
                    LocalDate today = LocalDate.now( z );
                    LocalDate oneMonthLater = today.plusMonths( 1 );
                    Long tsLong = System.currentTimeMillis()/1000;
                    String  ts = tsLong.toString();
                    MemoNoteModel memoNoteModel=new MemoNoteModel(somitiname.getText().toString(),fatherba.getText().toString(),mother.getText().toString(),
                            p_address.getText().toString(),votar.getText().toString(),"নতুন পণ্য",""+today,ts,firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                    firebaseFirestore.collection("ProductsNoteeelist")
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .collection("List")
                            .document(""+ts)
                            .set(memoNoteModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                        startActivity(new Intent(getApplicationContext(),NoteViewList.class));
                                    }
                                }
                            });



                }
            }
        });
        taskCard6=findViewById(R.id.taskCard6);
        taskCard6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(somitiname.getText().toString())|| TextUtils.isEmpty(fatherba.getText().toString())
                        ||TextUtils.isEmpty(mother.getText().toString())||TextUtils.isEmpty(p_address.getText().toString())
                        ||TextUtils.isEmpty(votar.getText().toString())) {
                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    final KProgressHUD progressDialog=  KProgressHUD.create(Memo_NoteCreate.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setLabel("তথ্য আপলোড করা হচ্ছে.....")
                            .setCancellable(false)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();
                    ZoneId z = ZoneId.of( "America/Montreal" );
                    LocalDate today = LocalDate.now( z );
                    LocalDate oneMonthLater = today.plusMonths( 1 );
                    Long tsLong = System.currentTimeMillis()/1000;
                    String  ts = tsLong.toString();
                    MemoNoteModel memoNoteModel=new MemoNoteModel(somitiname.getText().toString(),fatherba.getText().toString(),mother.getText().toString(),
                            p_address.getText().toString(),votar.getText().toString(),"মেয়াদোত্তীর্ণ  পণ্য",""+today,ts,firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                    firebaseFirestore.collection("ProductsNoteeelist")
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .collection("List")
                            .document(""+ts)
                            .set(memoNoteModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                        startActivity(new Intent(getApplicationContext(),NoteViewList.class));
                                    }
                                }
                            });



                }
            }
        });



    }
    Button dailyCheckCard,taskCard6;
    EditText somitiname,votar,p_address,mother,fatherba;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MemoHome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),MemoHome.class));
        return true;
    }

    public void addnote(View view) {
        Toasty.success(getApplicationContext(),"আপনি নোট বিভাগে আছেন",Toasty.LENGTH_SHORT,true).show();

    }

    public void newview(View view) {
        startActivity(new Intent(getApplicationContext(),NoteViewList.class));
    }
}