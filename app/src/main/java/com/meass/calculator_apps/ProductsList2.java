package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
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

public class ProductsList2 extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    ProductsAdapter getDataAdapter1;
    List<ProductAddmodel> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;
    TextView userlisst;

    SearchView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list2);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("প্রডাক্ট লিস্ট");
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
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        getList = new ArrayList<>();
        getDataAdapter1 = new ProductsAdapter(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =      firebaseFirestore.collection("ProductsList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List").document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductsList2.this));
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
        firebaseFirestore.collection("ProductsList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("productsname");
                            if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                ProductAddmodel add_customer=new ProductAddmodel(documentSnapshot.getString("productsname"),
                                        documentSnapshot.getString("sellprice")
                                        , documentSnapshot.getString("kenadam"),
                                        documentSnapshot.getString("stock"),
                                        documentSnapshot.getString("p_details"),
                                        //1
                                        documentSnapshot.getString("category")
                                        , documentSnapshot.getString("subcategory"),
                                        documentSnapshot.getString("stockalert"),
                                        documentSnapshot.getString("vatalert")
                                        ,
                                        //2
                                        documentSnapshot.getString("warentytime")
                                        , documentSnapshot.getString("discount"),
                                        documentSnapshot.getString("s_date"),
                                        documentSnapshot.getString("stopdate")
                                        ,
                                        documentSnapshot.getString("proimage")
                                        , documentSnapshot.getString("dateupload"),
                                        documentSnapshot.getString("time"),
                                        documentSnapshot.getString("email")
                                        ,
                                        documentSnapshot.getString("userid")
                                );
                                getList.add(add_customer);

                            }
                            getDataAdapter1 = new ProductsAdapter(getList);
                            recyclerView.setAdapter(getDataAdapter1);


                        }
                    }
                });
    }


    private void reciveData() {

        firebaseFirestore.collection("ProductsList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .orderBy("dateupload", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        ProductAddmodel get = ds.getDocument().toObject(ProductAddmodel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),BakiHome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),BakiHome.class));
        return true;
    }
}