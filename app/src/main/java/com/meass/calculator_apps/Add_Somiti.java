package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
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

public class Add_Somiti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__somiti);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("নতুন সমিতি");
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
        //edittext
        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        sovapoti_english=findViewById(R.id.sovapoti_english);
        fatherba=findViewById(R.id.fatherba);
        fatheren=findViewById(R.id.fatheren);
        mother=findViewById(R.id.mother);
        p_address=findViewById(R.id.p_address);
        ppaddress=findViewById(R.id.ppaddress);
        votar=findViewById(R.id.votar);
        natii=findViewById(R.id.natii);
        b_date=findViewById(R.id.b_date);
        s_kisti=findViewById(R.id.s_kisti);
        ocupa=findViewById(R.id.ocupa);
        refer=findViewById(R.id.refer);
        pasword=findViewById(R.id.pasword);
        signeture=findViewById(R.id.signeture);
        cirLoginButton=findViewById(R.id.cirLoginButton);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(somitiname.getText().toString()) || TextUtils.isEmpty(sovapoti.getText().toString()) || TextUtils.isEmpty(signeture.getText().toString()) ||
                        TextUtils.isEmpty(pasword.getText().toString()) || TextUtils.isEmpty(refer.getText().toString()) || TextUtils.isEmpty(ocupa.getText().toString()) || TextUtils.isEmpty(s_kisti.getText().toString()) || TextUtils.isEmpty(b_date.getText().toString()) ||
                        TextUtils.isEmpty(natii.getText().toString()) || TextUtils.isEmpty(votar.getText().toString()) || TextUtils.isEmpty(ppaddress.getText().toString()) || TextUtils.isEmpty(mother.getText().toString()) ||
                        TextUtils.isEmpty(fatheren.getText().toString()) || TextUtils.isEmpty(fatherba.getText().toString()) || TextUtils.isEmpty(sovapoti_english.getText().toString()) || TextUtils.isEmpty(p_address.getText().toString())

                ) {

                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Add_Somiti.this);
                        builder.setTitle("নিশ্চিতকরণ")
                                .setMessage("আপনি কি এটি তৈরি করতে চান?")
                                .setPositiveButton("না", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ZoneId z = ZoneId.of( "America/Montreal" );
                                LocalDate today = LocalDate.now( z );
                                LocalDate oneMonthLater = today.plusMonths( 1 );
                                Long tsLong = System.currentTimeMillis()/1000;
                               String  ts = tsLong.toString();
                                AddSomitimodel addSomitimodel=new AddSomitimodel(somitiname.getText().toString(),sovapoti.getText().toString(),signeture.getText().toString(),pasword.getText().toString(),refer.getText().toString(),ocupa.getText().toString(),s_kisti.getText().toString(),
                                        b_date.getText().toString(),natii.getText().toString(),votar.getText().toString(),ppaddress.getText().toString(),p_address.getText().toString(),mother.getText().toString(),fatheren.getText().toString(),
                                        fatherba.getText().toString(),sovapoti_english.getText().toString(),""+today,ts,firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());

                                final KProgressHUD progressDialog=  KProgressHUD.create(Add_Somiti.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("তথ্য আপলোড করা হচ্ছে.....")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                firebaseFirestore.collection("SomitiList")
                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                        .collection("List")
                                        .document(""+somitiname.getText().toString().toLowerCase())
                                        .set(addSomitimodel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    progressDialog.dismiss();
                                                    Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                                    startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
                                                    return;
                                                }
                                            }
                                        });



                            }
                        }).create();
                        builder.show();
                }
            }
        });

    }
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Button cirLoginButton;
    EditText somitiname,sovapoti,signeture,pasword,refer,ocupa,s_kisti,
            b_date,natii,votar,ppaddress,p_address,mother,fatheren,
            fatherba,sovapoti_english;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
    }
    ///somitiname,sovapoti,signeture,pasword,refer,ocupa,s_kisti,
    //            b_date,natii,votar,ppaddress,p_address,mother,fatheren,
    //            fatherba,sovapoti_english
    ///s_name,relagi,porichoy,mysigne,datee,s_ppaer,s_paddress,relation
    //namefirst,namesecond,onomoshonsig,date2,idNumber,takakka,grohonsa
    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
        return true;
    }
}