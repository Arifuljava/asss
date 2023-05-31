package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import es.dmoral.toasty.Toasty;

public class Addbaki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbaki);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("অ্যাড বাকি");
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

        //
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        customer_name=findViewById(R.id.customer_name);
        c_number=findViewById(R.id.c_number);
        jomafirst=findViewById(R.id.jomafirst);
        productsname=findViewById(R.id.productsname);
        produtsquan=findViewById(R.id.produtsquan);
        price=findViewById(R.id.price);
        ///dialouge
         mDialog = new Dialog(Addbaki.this);


        //mDialog = new Dialog(HomeACTIVITY.this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //LayoutInflater factory = LayoutInflater.from(this);

        mDialog.setContentView(R.layout.dialouge2);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FloatingActionButton dialogClose=(FloatingActionButton)mDialog.findViewById(R.id.dialogClose);
        EditText methodename=(EditText)mDialog.findViewById(R.id.methodename);

        EditText minwith=(EditText)mDialog.findViewById(R.id.minwith);
        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        totalproducts=findViewById(R.id.totalproducts);
        totalamount=findViewById(R.id.totalamount);
        resultt=findViewById(R.id.resultt);
        sovapoti=findViewById(R.id.sovapoti);
        Button login_button=(Button)mDialog.findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(methodename.getText().toString())||TextUtils.isEmpty(minwith.getText().toString())) {
                    Toasty.success(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                }
                else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String  ts = tsLong.toString();
                    Model1 model1=new Model1(methodename.getText().toString(),minwith.getText().toString(),ts,customer_name.getText().toString().toLowerCase());
                    firebaseFirestore.collection("BakiaddListDemo")
                            .document(firebaseAuth.getCurrentUser().getEmail())
                            .collection(customer_name.getText().toString().toLowerCase())
                            .document(ts)
                            .set(model1)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        mDialog.dismiss();
                                        getAlldata();
                                        Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                        return;
                                    }
                                }
                            });
                }
            }
        });
      if (TextUtils.isEmpty(customer_name.getText().toString().toLowerCase())) {
      }
      else {
          getAlldata();
      }
        bakidibe=findViewById(R.id.bakidibe);
        customer_name.addTextChangedListener(namewatcher);
        jomafirst.addTextChangedListener(jomawatcher);

        Button bakidddd=findViewById(R.id.bakidddd);
        bakidddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(customer_name.getText().toString())||TextUtils.isEmpty(c_number.getText().toString())
                || TextUtils.isEmpty(jomafirst.getText().toString())||TextUtils.isEmpty(productsname.getText().toString())
                ||TextUtils.isEmpty(produtsquan.getText().toString())||TextUtils.isEmpty(price.getText().toString())) {
                    Toasty.success(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                }
                else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Addbaki.this);
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
                            final KProgressHUD progressDialog=  KProgressHUD.create(Addbaki.this)
                                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                    .setLabel("তথ্য আপলোড করা হচ্ছে.....")
                                    .setCancellable(false)
                                    .setAnimationSpeed(2)
                                    .setDimAmount(0.5f)
                                    .show();
                            BakiMainModel bakiMainModel=new BakiMainModel(customer_name.getText().toString(),c_number.getText().toString(),
                                    jomafirst.getText().toString(),productsname.getText().toString(),produtsquan.getText().toString(),price.getText().toString()
                            ,message,totalproducts.getText().toString(),totalamount.getText().toString(),bakidibe.getText().toString(),""+today,ts,firebaseAuth.getCurrentUser().getUid(),
                                    firebaseAuth.getCurrentUser().getEmail());
                            firebaseFirestore.collection("BakiLissst")
                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                    .collection("List")
                                    .document(""+ts)
                                    .set(bakiMainModel)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                firebaseFirestore.collection("BakiaddListDemo")
                                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                                        .collection(customer_name.getText().toString().toLowerCase())
                                                        .get()
                                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                if(task.isSuccessful()) {
                                                                    int ncount=0;
                                                                    double mainamount=0;
                                                                    for (DocumentSnapshot document : task.getResult()) {
                                                                        ncount++;
                                                                    }
                                                                    totalproducts.setText(""+ncount);
                                                                    if (ncount==0) {
                                                                    }
                                                                    else {
                                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                                            String latitude2 = document.getString("time");
                                                                            firebaseFirestore.collection("BakiaddListDemo")
                                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                    .collection(customer_name.getText().toString().toLowerCase())
                                                                                    .document(latitude2)
                                                                                    .delete()
                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                                        }
                                                                                    });

                                                                            // Toast.makeText(Addbaki.this, ""+message, Toast.LENGTH_SHORT).show();

                                                                        }
                                                                    }

                                                                }
                                                            }
                                                        });

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

                                                                          String  sponsor_income=task.getResult().getString("sponsor_income");
                                                                          double ddd=Double.parseDouble(sponsor_income)+Double.parseDouble(bakidibe.getText().toString());
                                                                            firebaseFirestore.collection("Users")
                                                                                    .document(firebaseAuth.getCurrentUser().getUid())
                                                                                    .collection("Main_Balance")
                                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                    .update("sponsor_income",""+ddd)
                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                progressDialog.dismiss();
                                                                                                Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                                                                                startActivity(new Intent(getApplicationContext(),BakiHome.class));
                                                                                                return;
                                                                                            }
                                                                                        }
                                                                                    });


                                                                        } catch (Exception e) {

                                                                        }
                                                                    } else {

                                                                    }
                                                                }
                                                            }
                                                        });


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
    String message="";
    TextWatcher jomawatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
String check=s.toString();
if (TextUtils.isEmpty(check)) {
}
else
{
    double first=Double.parseDouble(totalamount.getText().toString());
    double second=Double.parseDouble(jomafirst.getText().toString());
    double dibe=first-second;
    bakidibe.setText(""+dibe);
}
        }
    };
    EditText sovapoti,bakidibe;
    Button totalproducts,resultt,totalamount;
TextWatcher namewatcher=new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
String check=s.toString();
if (TextUtils.isEmpty(check)) {
}
else {
    getAlldata();
    sovapoti.setText(customer_name.getText().toString());

}
    }
};
    private StaggeredGridLayoutManager mLayoutManager;
    private void getAlldata() {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        getList = new ArrayList<>();
        getDataAdapter1 = new DemoProductsAdapter(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference  =      firebaseFirestore.collection("BakiaddListDemo")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection(customer_name.getText().toString().toLowerCase()).document();
        recyclerView =findViewById(R.id.rreeeed);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(getDataAdapter1);
        ///
        firebaseFirestore.collection("BakiaddListDemo")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection(customer_name.getText().toString().toLowerCase())
                .orderBy("time", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        Model1 get = ds.getDocument().toObject(Model1.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }

                }
            }
        });
        ///getalldata
        firebaseFirestore.collection("BakiaddListDemo")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection(customer_name.getText().toString().toLowerCase())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            int ncount=0;
                            double mainamount=0;
                            for (DocumentSnapshot document : task.getResult()) {
                                ncount++;
                            }
                            totalproducts.setText(""+ncount);
                            if (ncount==0) {
                            }
                            else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String latitude2 = document.getString("price");
                                    String productsname = document.getString("productsname");
                                    message=message+""+productsname+" = "+latitude2+"\n";
                                    mainamount+=Double.parseDouble(latitude2);
                                    totalamount.setText(""+mainamount);
                                   if (TextUtils.isEmpty(jomafirst.getText().toString())) {
                                       bakidibe.setText(""+mainamount);
                                   }
                                   else {
                                       double dd=mainamount=Double.parseDouble(jomafirst.getText().toString());
                                       bakidibe.setText(""+dd);
                                   }

                                   // Toast.makeText(Addbaki.this, ""+message, Toast.LENGTH_SHORT).show();

                                }
                            }

                        }
                    }
                });

    }

    //recycler
    LottieAnimationView empty_cart;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    DemoProductsAdapter getDataAdapter1;
    List<Model1> getList;
    String url;

    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    String cus_name;
    TextView userlisst;

    SearchView name;

    ///
     Dialog mDialog;
    EditText customer_name,price,produtsquan,productsname,jomafirst,c_number;
FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),BakiHome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),BakiHome.class));
        return true;
    }

    public void add_user(View view) {
        startActivity(new Intent(getApplicationContext(),ProductsList.class));
    }

    public void addProductssss(View view) {
        if (TextUtils.isEmpty(customer_name.getText().toString())) {
            Toasty.success(getApplicationContext(),"ক্রেতার নাম লিখুন",Toasty.LENGTH_SHORT,true).show();


        }
        else {
mDialog.show();

        }
    }
}