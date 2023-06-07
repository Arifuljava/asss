package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import es.dmoral.toasty.Toasty;

public class DailyCash extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Button tarikdetect,datess,bakiedit,cashedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_cash);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("হিস্টরি");
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
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        tarikdetect=findViewById(R.id.tarikdetect);
        datess=findViewById(R.id.datess);
        bakiedit=findViewById(R.id.bakiedit);
        cashedit=findViewById(R.id.cashedit);
        tarikdetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog mDialog = new Dialog(DailyCash.this);


                //mDialog = new Dialog(HomeACTIVITY.this);
                mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                //LayoutInflater factory = LayoutInflater.from(this);

                mDialog.setContentView(R.layout.adddate);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                FloatingActionButton dialogClose=(FloatingActionButton)mDialog.findViewById(R.id.dialogClose);
                EditText methodename=(EditText)mDialog.findViewById(R.id.methodename);
                EditText minwith=(EditText)mDialog.findViewById(R.id.minwith);
                EditText paymentnumber_your=(EditText)mDialog.findViewById(R.id.paymentnumber_your);
                TextView resulttt=(TextView) mDialog.findViewById(R.id.resulttt);
                Button login_button=(Button)mDialog.findViewById(R.id.login_button);
                login_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String  input;
                        String first=methodename.getText().toString();
                        String second=minwith.getText().toString();
                        String third = paymentnumber_your.getText().toString();
                        if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)||TextUtils.isEmpty(third)) {
                            Toasty.error(getApplicationContext(),"তারিখ লিখুন",Toasty.LENGTH_SHORT,true).show();
                        }
                        else {
                            mDialog.dismiss();
                            String today = first+""+second+""+third;
                              input = today;
                            input = input.replace(" ", "");

                            String maindate = third+"-"+second+"-"+first;
                            tarikdetect.setText(maindate);
                            datess.setText(""+maindate);
                            String finalInput = input;
                            Toast.makeText(DailyCash.this, ""+finalInput, Toast.LENGTH_SHORT).show();
                            firebaseFirestore.collection("DailyCash_Daily")
                                    .document(firebaseAuth.getCurrentUser().getUid())
                                    .collection(""+input)
                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                if (task.getResult().exists()) {
                                                 try {
                                                     bakiedit.setText(task.getResult().getString("baki"));
                                                     cashedit.setText(task.getResult().getString("coin"));

                                                     firebaseAuth= FirebaseAuth.getInstance();
                                                     firebaseFirestore= FirebaseFirestore.getInstance();

                                                     getList = new ArrayList<>();
                                                     getDataAdapter1 = new CashAdapter_2(getList,"detecor","gettingtext");
                                                     firebaseFirestore = FirebaseFirestore.getInstance();
                                                     documentReference  =      firebaseFirestore.collection("DailyCash_Daily_List")
                                                             .document(firebaseAuth.getCurrentUser().getUid())
                                                             .collection(""+ finalInput.toString())
                                                             .document(firebaseAuth.getCurrentUser().getEmail())
                                                             .collection("List").document();
                                                     recyclerView =findViewById(R.id.rreeeed);
                                                     recyclerView.setHasFixedSize(true);
                                                     recyclerView.setLayoutManager(new LinearLayoutManager(DailyCash.this));
                                                     recyclerView.setAdapter(getDataAdapter1);

                                                     firebaseFirestore.collection("DailyCash_Daily_List")
                                                             .document(firebaseAuth.getCurrentUser().getUid())
                                                             .collection(""+finalInput)
                                                             .document(firebaseAuth.getCurrentUser().getEmail())
                                                             .collection("List")
                                                           .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                                         @Override
                                                         public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                                             for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                                                                 if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                                                                     AddCashmodel get = ds.getDocument().toObject(AddCashmodel.class);
                                                                     getList.add(get);
                                                                     getDataAdapter1.notifyDataSetChanged();
                                                                 }

                                                             }
                                                         }
                                                     });


                                                 }catch (Exception e) {
                                                 }

                                                }
                                                else {
                                                    Toast.makeText(getApplicationContext(), "কোন তথ্য পাওয়া যায়নি", Toast.LENGTH_SHORT).show();
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
        });
    }
    DocumentReference documentReference;
    RecyclerView recyclerView;
    CashAdapter_2 getDataAdapter1;
    List<AddCashmodel> getList;

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),CashHome.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),CashHome.class));
    }

    public void addtocash(View view) {
        startActivity(new Intent(getApplicationContext(),CashHome.class));
    }
}