package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class AddCallListActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    ContactAdapter22 getDataAdapter1;
    List<ContactModel> getList;
    String url;
    String detecor,gettingtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call_list);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("কল");
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
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        getList = new ArrayList<>();
        getDataAdapter1 = new ContactAdapter22(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =        firebaseFirestore.collection("ContactList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List").document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddCallListActivity.this));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
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
        firebaseFirestore.collection("ContactList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("name");
                            if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                ContactModel add_customer=new ContactModel(documentSnapshot.getString("name"),
                                        documentSnapshot.getString("number")
                                        , documentSnapshot.getString("details"),
                                        documentSnapshot.getString("email"),
                                        documentSnapshot.getString("time"),
                                        //1
                                        documentSnapshot.getString("uuid")

                                );
                                getList.add(add_customer);

                            }
                            getDataAdapter1 = new ContactAdapter22(getList);
                            recyclerView.setAdapter(getDataAdapter1);


                        }
                    }
                });
    }
    public void sports(View view) {
        startActivity(new Intent(getApplicationContext(),ContactHome.class));
    }

    public void kistigrohon(View view) {
        startActivity(new Intent(getApplicationContext(),ContactHome.class));
    }
    SearchView name;
    private void reciveData() {

        firebaseFirestore.collection("ContactList")
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
                        ContactModel get = ds.getDocument().toObject(ContactModel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }

    public void add_balance(View view) {
        Intent intent=new Intent(getApplicationContext(),AddCallActivity.class);
        intent.putExtra("detctor","2");
        intent.putExtra("name","a");
        intent.putExtra("number","a");
     startActivity(intent);
    }

    public void cccccccc(View view) {
        Intent intent=new Intent(getApplicationContext(),CallListActivity.class);
        intent.putExtra("detctor","2");
        intent.putExtra("name","a");
        intent.putExtra("number","a");
     startActivity(intent);

    }
}