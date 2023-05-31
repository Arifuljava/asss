package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class BakiHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baki_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("বাকি");
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

        //////
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();



        ///
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        getList = new ArrayList<>();
        getDataAdapter1 = new BakiAdapter(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =       firebaseFirestore.collection("BakiLissst")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List").document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(BakiHome.this));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
        //searching
        name=findViewById(R.id.serachname);
        name.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //fullsearch(query);

                //phoneSerach(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchAllUser(newText);

                //phoneSerach1(newText);
                return false;
            }
        });
    }
    private void searchAllUser(String newText) {
        firebaseFirestore.collection("BakiLissst")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("c_name");
                            if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                BakiMainModel add_customer=new BakiMainModel(documentSnapshot.getString("c_name"),
                                        documentSnapshot.getString("c_number")
                                        , documentSnapshot.getString("joma"),
                                        documentSnapshot.getString("p_name"),
                                        documentSnapshot.getString("p_quan"),
                                        //1
                                        documentSnapshot.getString("p_price")
                                        , documentSnapshot.getString("mesaage"),
                                        documentSnapshot.getString("totalproducts"),
                                        documentSnapshot.getString("totalamount")
                                        ,
                                        //2
                                        documentSnapshot.getString("bakidibe")
                                        , documentSnapshot.getString("date"),
                                        documentSnapshot.getString("time"),
                                        documentSnapshot.getString("uuid")
                                        ,
                                        documentSnapshot.getString("email")
                                );
                                getList.add(add_customer);

                            }
                            getDataAdapter1 = new BakiAdapter(getList);
                            recyclerView.setAdapter(getDataAdapter1);


                        }
                    }
                });
    }


    private void reciveData() {

        firebaseFirestore.collection("BakiLissst")
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
                        BakiMainModel get = ds.getDocument().toObject(BakiMainModel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    BakiAdapter getDataAdapter1;
    List<BakiMainModel> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;
    TextView userlisst;

    SearchView name;

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(), HomeACTIVITY.class));

        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), HomeACTIVITY.class));
    }

    public void addBaki(View view) {
        startActivity(new Intent(getApplicationContext(), Addbaki.class));
    }

    public void productslist(View view) {
        startActivity(new Intent(getApplicationContext(), ProductsList2.class));
    }

    public void bakihome(View view) {
        startActivity(new Intent(getApplicationContext(), BakiList.class));
    }
}