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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Memo_create extends AppCompatActivity {
    AutoCompleteTextView fatheren;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_create);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("কোম্পানির ম্যামো");
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
        //fire
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        fatheren=findViewById(R.id.fatheren);
        firebaseFirestore.collection("Personal")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> names = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.getString("name");
                                names.add(name);
                            }

                            // Create an ArrayAdapter and set it to the AutoCompleteTextView
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(Memo_create.this,
                                    android.R.layout.simple_dropdown_item_1line, names);
                            fatheren.setAdapter(adapter);
                        } else {
                            Log.d("Firestore", "Error getting documents: ", task.getException());
                        }
                    }
                });
        //edittext
        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        sovapoti_english=findViewById(R.id.sovapoti_english);
        fatherba=findViewById(R.id.fatherba);

        mother=findViewById(R.id.mother);
        p_address=findViewById(R.id.p_address);
        ektir=findViewById(R.id.ektir);
        votar=findViewById(R.id.votar);
        natii=findViewById(R.id.natii);
        b_date=findViewById(R.id.b_date);
        s_kisti=findViewById(R.id.s_kisti);
        refer=findViewById(R.id.refer);
        ocupa=findViewById(R.id.ocupa);
        //button
        dailyCheckCard=findViewById(R.id.dailyCheckCard);
        taskCard6=findViewById(R.id.taskCard6);
        taskCard61=findViewById(R.id.taskCard61);
        cirLoginButton=findViewById(R.id.cirLoginButton);
        //textwatcher
        fatherba.addTextChangedListener(textproducts);
        p_address.addTextChangedListener(textpricee);
        ektir.addTextChangedListener(textpricee1);
        natii.addTextChangedListener(commission);
        s_kisti.addTextChangedListener(finaltextt);
        //clickable
        dailyCheckCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyCheckCard.setBackgroundColor(Color.RED);
                status="বাকি ";
            }
        });
        taskCard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status="বাকি ৩ দিন পরে ";
            }
        });
        sikar=findViewById(R.id.sikar);
        taskCard61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if (flag==0) {
                 String message=fatherba.getText().toString()+" "+fatheren.getText().toString()+"   "+p_address.getText().toString()+" : পিস\n" +
                         "দাম      : "+mainvalue+" টাকা\n" +
                         "নগদ      : "+s_kisti.getText().toString()+" টাকা\n" +
                         "বাকি       : "+ocupa.getText().toString()+" টাকা\n" +
                         "\n\n"+status;
                 sikar.setVisibility(View.VISIBLE);
                 flag=1;
             }
             else if (flag==1) {
                 String message=fatherba.getText().toString()+" "+fatheren.getText().toString()+"   "+p_address.getText().toString()+" : পিস\n" +
                         "দাম      : "+mainvalue+" টাকা\n" +
                         "নগদ      : "+s_kisti.getText().toString()+" টাকা\n" +
                         "বাকি       : "+ocupa.getText().toString()+" টাকা\n" +
                         "\n\n"+status;
                 sikar.setVisibility(View.GONE);
                 flag=0;
             }
            }
        });

        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(somitiname.getText().toString())||
                        TextUtils.isEmpty(sovapoti.getText().toString())||
                        TextUtils.isEmpty(sovapoti_english.getText().toString())||
                        TextUtils.isEmpty(fatherba.getText().toString())||
                        TextUtils.isEmpty(fatheren.getText().toString())||
                        TextUtils.isEmpty(mother.getText().toString())||
                        TextUtils.isEmpty(p_address.getText().toString())||
                        TextUtils.isEmpty(ektir.getText().toString())||
                        TextUtils.isEmpty(votar.getText().toString())||
                        TextUtils.isEmpty(natii.getText().toString())||
                        TextUtils.isEmpty(b_date.getText().toString())||
                        TextUtils.isEmpty(s_kisti.getText().toString())||
                        TextUtils.isEmpty(ocupa.getText().toString())||
                        TextUtils.isEmpty(refer.getText().toString())) {
                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Memo_create.this);
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





                            firebaseFirestore.collection("ProductsList")
                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                    .collection("List")
                                    .document(fatherba.getText().toString().toLowerCase().toString())
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                if (task.getResult().exists()) {
                                                    String stock=task.getResult().getString("stock");
                                                    if (Double.parseDouble(stock)<Double.parseDouble(p_address.getText().toString())) {

                                                        Toasty.error(getApplicationContext(),"পর্যাপ্ত পণ্য নেই",Toasty.LENGTH_SHORT,true).show();
                                                        return;
                                                    }
                                                    else {
                                                        final KProgressHUD progressDialog=  KProgressHUD.create(Memo_create.this)
                                                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                                                .setLabel("তথ্য আপলোড করা হচ্ছে.....")
                                                                .setCancellable(false)
                                                                .setAnimationSpeed(2)
                                                                .setDimAmount(0.5f)
                                                                .show();
                                                        Map<String, Object> user = new HashMap<>();
                                                        user.put("name", ""+fatheren.getText().toString());

                                                        firebaseFirestore.collection("Personal")
                                                                .add(user)
                                                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentReference> task) {

                                                                    }
                                                                });
                                                        MemoProductsModel memoProductsModel=new MemoProductsModel(somitiname.getText().toString(),
                                                                sovapoti.getText().toString(),sovapoti_english.getText().toString(),fatherba.getText().toString(),
                                                                fatheren.getText().toString(),mother.getText().toString(),p_address.getText().toString(),
                                                                ektir.getText().toString(),votar.getText().toString()
                                                                ,natii.getText().toString(),b_date.getText().toString(),s_kisti.getText().toString(),
                                                                ocupa.getText().toString(),refer.getText().toString()
                                                                ,status,""+today,ts,firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                                                        double dddd=Double.parseDouble(stock)-Double.parseDouble(p_address.getText().toString());
                                                        firebaseFirestore.collection("ProductsList")
                                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                                .collection("List")
                                                                .document(fatherba.getText().toString().toLowerCase().toString())
                                                                .update("stock",""+dddd)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                    }
                                                                });

                                                        firebaseFirestore.collection("MainMemo")
                                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                                .collection("List")
                                                                .document(""+ts)
                                                                .set(memoProductsModel)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            progressDialog.dismiss();
                                                                            Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                                                            startActivity(new Intent(getApplicationContext(),MemoHome.class));
                                                                            return;
                                                                        }
                                                                    }
                                                                });

                                                    }
                                                }
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
    int flag=0;

    String status;
    EditText ocupa,sikar;
    String kk;
    double mainvalue;
    double sssss;
    TextWatcher finaltextt=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
kk=s.toString();
if (TextUtils.isEmpty(kk)) {
}
else {
     sssss =mainvalue-(Double.parseDouble(s_kisti.getText().toString()));
    ocupa.setText(""+sssss);

}
        }
    };
    String commi;

    TextWatcher commission=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
commi=s.toString();
if (TextUtils.isEmpty(commi)) {
    b_date.setText("");
}
else {
 if (TextUtils.isEmpty(votar.getText().toString())) {
     b_date.setText("");
 }
 else {
     String dd=votar.getText().toString();
     double dddd=(Double.parseDouble(dd)*Double.parseDouble(commi))/100;
     mainvalue=Double.parseDouble(dd)-dddd;
     b_date.setText(""+dddd);
 }
}
        }
    };
    String chekrate;
    TextWatcher textpricee=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
chekrate=s.toString();
if(TextUtils.isEmpty(chekrate)) {
    votar.setText("");
}
else {
   if (TextUtils.isEmpty(ektir.getText().toString())) {
       votar.setText("");
   }
   else {
       double ddd = Double.parseDouble(ektir.getText().toString())*Double.parseDouble(p_address.getText().toString());
       votar.setText(""+ddd);
   }
}
        }
    };
    TextWatcher textpricee1=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            chekrate=s.toString();
            if(TextUtils.isEmpty(chekrate)) {
                votar.setText("");
            }
            else {
                if (TextUtils.isEmpty(p_address.getText().toString())) {
                    votar.setText("");
                }
                else {
                    double ddd = Double.parseDouble(ektir.getText().toString())*Double.parseDouble(p_address.getText().toString());
                    votar.setText(""+ddd);
                }
            }
        }
    };
    String check;
    TextWatcher textproducts=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
check=s.toString();
if (TextUtils.isEmpty(check)) {
}
else {
    firebaseFirestore.collection("ProductsList")
            .document(firebaseAuth.getCurrentUser().getEmail())
            .collection("List")
            .document(fatherba.getText().toString().toLowerCase().toString())
            .get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            fatherba.setError("আপনার তালিকায় পণ্যটি বিদ্যমান");
                            dailyCheckCard.setEnabled(true);
                            taskCard6.setEnabled(true);
                          //  cirLoginButton.setEnabled(true);
                            taskCard61.setEnabled(true);
                            ektir.setText(task.getResult().getString("sellprice"));
                        }
                        else {
                            fatherba.setError("আপনার তালিকায় পণ্যটি বিদ্যমান নয়");
                            dailyCheckCard.setEnabled(false);
                            taskCard6.setEnabled(false);
                        //    cirLoginButton.setEnabled(false);
                            taskCard61.setEnabled(false);
                        }
                    }
                }
            });
}
        }
    };
    Button dailyCheckCard,taskCard6,cirLoginButton,taskCard61;
    EditText somitiname,sovapoti,sovapoti_english,fatherba,mother,p_address
            ,ektir,votar,natii,b_date,s_kisti,refer;
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
}