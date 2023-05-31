package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import es.dmoral.toasty.Toasty;

public class Add_Kisti extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    AdapterSub6 getDataAdapter1;
    List<AddmemberModel> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;
    TextView userlisst;

    SearchView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__kisti);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("কিস্তি গ্রহণ");
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
        getDataAdapter1 = new AdapterSub6(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =     firebaseFirestore.collection("SomitiMember")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List").document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Add_Kisti.this));
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
        firebaseFirestore.collection("SomitiMember")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta = documentSnapshot.getString("sovapoti");
                            if (dta.toLowerCase().toString().contains(newText.toLowerCase().toString())) {
                                AddmemberModel add_customer=new AddmemberModel(documentSnapshot.getString("somitiname"),
                                        documentSnapshot.getString("sovapoti")
                                        , documentSnapshot.getString("signeture"),
                                        documentSnapshot.getString("pasword"),
                                        documentSnapshot.getString("refer"),
                                        //1
                                        documentSnapshot.getString("ocupa")
                                        , documentSnapshot.getString("s_kisti"),
                                        documentSnapshot.getString("b_date"),
                                        documentSnapshot.getString("natii")
                                        ,
                                        //2
                                        documentSnapshot.getString("votar")
                                        , documentSnapshot.getString("ppaddress"),
                                        documentSnapshot.getString("p_address"),
                                        documentSnapshot.getString("mother")
                                        ,
                                        documentSnapshot.getString("fatheren")
                                        , documentSnapshot.getString("fatherba"),
                                        documentSnapshot.getString("sovapoti_english"),
                                        documentSnapshot.getString("date")
                                        ,
                                        documentSnapshot.getString("relationname")
                                        , documentSnapshot.getString("relationship"),
                                        documentSnapshot.getString("relationshipplcae"),
                                        documentSnapshot.getString("repl1")
                                        //5
                                        ,
                                        documentSnapshot.getString("payment")
                                        , documentSnapshot.getString("id_number"),
                                        documentSnapshot.getString("image"),
                                        documentSnapshot.getString("email")
                                        ,
                                        documentSnapshot.getString("uuid")
                                        , documentSnapshot.getString("time")
                                );
                                getList.add(add_customer);

                            }
                            getDataAdapter1 = new AdapterSub6(getList);
                            recyclerView.setAdapter(getDataAdapter1);


                        }
                    }
                });
    }


    private void reciveData() {

        firebaseFirestore.collection("SomitiMember")
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
                        AddmemberModel get = ds.getDocument().toObject(AddmemberModel.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });

    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),Sonchoyhome.class));
        return true;
    }

    public void add_balance(View view) {
        final Dialog mDialog = new Dialog(Add_Kisti.this);


        //mDialog = new Dialog(HomeACTIVITY.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //LayoutInflater factory = LayoutInflater.from(this);

        mDialog.setContentView(R.layout.ddi);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FloatingActionButton dialogClose=(FloatingActionButton)mDialog.findViewById(R.id.dialogClose);
        EditText methodename=(EditText)mDialog.findViewById(R.id.methodename);
        EditText minwith=(EditText)mDialog.findViewById(R.id.minwith);
        TextView resulttt=(TextView) mDialog.findViewById(R.id.resulttt);
        Button login_button=(Button)mDialog.findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String []lo={"যোগ","বিয়োগ","গুণ","ভাগ"};
                AlertDialog.Builder builder=new AlertDialog.Builder(Add_Kisti.this);
                builder.setTitle("ক্যালকুলেটর")
                        .setItems(lo, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which==0) {
                                    String first=methodename.getText().toString();
                                    String second=minwith.getText().toString();
                                    if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
                                        Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
                                    }
                                    else {
                                        resulttt.setVisibility(View.VISIBLE);
                                        double add=Double.parseDouble(first)+Double.parseDouble(second);
                                        resulttt.setText("ফলাফল  : "+add);

                                    }
                                }
                                else if (which==1) { String first=methodename.getText().toString();
                                    String second=minwith.getText().toString();
                                    if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
                                        Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
                                    }
                                    else {
                                        resulttt.setVisibility(View.VISIBLE);
                                        double add=Double.parseDouble(first)-Double.parseDouble(second);
                                        resulttt.setText("ফলাফল  : "+add);

                                    }

                                }
                                else if(which==2) {
                                    String first=methodename.getText().toString();
                                    String second=minwith.getText().toString();
                                    if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
                                        Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
                                    }
                                    else {
                                        resulttt.setVisibility(View.VISIBLE);
                                        double add=Double.parseDouble(first)*Double.parseDouble(second);
                                        resulttt.setText("ফলাফল  : "+add);

                                    }
                                }
                                else if(which==3) {
                                    String first=methodename.getText().toString();
                                    String second=minwith.getText().toString();
                                    if (TextUtils.isEmpty(first)||TextUtils.isEmpty(second)) {
                                        Toasty.error(getApplicationContext(),"সংখ্যা লিখুন",Toasty.LENGTH_SHORT,true).show();
                                    }
                                    else {
                                        resulttt.setVisibility(View.VISIBLE);
                                        double add=Double.parseDouble(first)/Double.parseDouble(second);
                                        resulttt.setText("ফলাফল  : "+add);

                                    }
                                }

                            }
                        }).create();
                builder.show();
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
       // startActivity(new Intent(getApplicationContext(),SimpleCalculator.class));
    }
}