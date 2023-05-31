package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

public class MemoHome extends AppCompatActivity {
FirebaseFirestore firebaseFirestore;
FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("মেমো");
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
        deposit__history=findViewById(R.id.deposit__history);
        withdrawlisst=findViewById(R.id.withdrawlisst);
        change_password=findViewById(R.id.change_password);
        appsdownload=findViewById(R.id.appsdownload);
        ///products details
        firebaseFirestore.collection("ProductsList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int ncount=0;
                            for (DocumentSnapshot document : task.getResult()) {
                                ncount++;
                            }
                            if (ncount==0) {

                            }
                            else {
                                double total=0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String stock = document.getString("stock");
                                    String stopdate = document.getString("stopdate");
                                    total+=Double.parseDouble(stock);
                                    deposit__history.setText("টোটাল পণ্য আছে : "+total+" টি");
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                    Calendar calendar1 = Calendar.getInstance();
                                    Calendar calendar2 = Calendar.getInstance();

                                    try {
                                        int seci=0;
                                        ZoneId z = ZoneId.of( "Asia/Dhaka" );
                                        LocalDate today = LocalDate.now( z );
                                      Date  date1 = dateFormat.parse(stopdate);
                                        Date date2 = dateFormat.parse(String.valueOf(today));
                                        long diff = date1.getTime() - date2.getTime();
                                        long diff1 = date2.getTime() - date1.getTime();
                                        int time= (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                        int time1= (int) TimeUnit.DAYS.convert(diff1, TimeUnit.MILLISECONDS);
                                        int timecount=0;
                                        if (time<=5) {
                                            timecount+=1;
                                            withdrawlisst.setText("মজুত পণ্য শেষের দিকে : "+timecount+" টি");
                                        }
                                        else if (time1==0) {
                                            seci+=1;
                                            appsdownload.setText("মেয়াদ উত্তীর্ন পণ্য  :  "+seci+" টি");
                                        }

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }
                        }
                    }
                });
        ZoneId z = ZoneId.of( "Asia/Dhaka" );
        LocalDate today = LocalDate.now( z );
        firebaseFirestore.collection("ProductsList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("New")
                .document(""+today)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                change_password.setText("নতুন পণ্য সংখ্যা : "+task.getResult().getString("stock")+" টি");
                            }
                            else {
                                change_password.setText("নতুন পণ্য সংখ্যা : 0"+" টি");
                            }
                        }
                        else {
                            change_password.setText("নতুন পণ্য সংখ্যা : 0"+" টি");
                        }
                    }
                });
        deposit__history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyProducts.class));
            }
        });
        ///dataget
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        getList = new ArrayList<>();
        getDataAdapter1 = new MemoMainAdapterre(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =     firebaseFirestore.collection("MainMemo")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List").document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MemoHome.this));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();



    }
    private void reciveData() {

        firebaseFirestore.collection("MainMemo")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .orderBy("time", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        MemoProductsModel get = ds.getDocument().toObject(MemoProductsModel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }

    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    MemoMainAdapterre getDataAdapter1;
    List<MemoProductsModel> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;
    TextView userlisst;

    SearchView name;
    TextView deposit__history,withdrawlisst,change_password,appsdownload;
    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeACTIVITY.class));
    }

    public void memocreate(View view) {
        startActivity(new Intent(getApplicationContext(),Memo_create.class));
    }

    public void add_products(View view) {
        startActivity(new Intent(getApplicationContext(),AddProducts.class));
    }

    public void memocreate_products(View view) {
        startActivity(new Intent(getApplicationContext(),Memo_NoteCreate.class));
    }
}