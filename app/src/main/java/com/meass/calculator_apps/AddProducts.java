package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

public class AddProducts extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
    EditText Email_Log;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView no_of_items, total_amount, spinner4;
    String package_name, package_price, packing;
    EditText spinner1, spinner2;
    Button upgrade;
    KProgressHUD kProgressHUD;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("পণ্যর বিস্তারিত");
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

        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        //edittext
        somitiname=findViewById(R.id.somitiname);
        sovapoti=findViewById(R.id.sovapoti);
        sovapoti_english=findViewById(R.id.sovapoti_english);
        fatherba=findViewById(R.id.fatherba);
        fatheren=findViewById(R.id.fatheren);
        mother=findViewById(R.id.mother);
        p_address=findViewById(R.id.p_address);
        natii=findViewById(R.id.natii);
        b_date=findViewById(R.id.b_date);
        s_kisti=findViewById(R.id.s_kisti);
        ocupa=findViewById(R.id.ocupa);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        //check
        ppaddress=findViewById(R.id.ppaddress);
        votar=findViewById(R.id.votar);
        imagee=findViewById(R.id.imagee);
        cirLoginButton11=findViewById(R.id.cirLoginButton);
        imagee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 21);
            }
        });
        ///cate

        mother.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.productcate);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner_row, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mother.setAdapter(adapter);

        //second
        p_address.setOnItemSelectedListener(this);

        String[] textSizes1 = getResources().getStringArray(R.array.subcos);
        ArrayAdapter adapter1 = new ArrayAdapter(this,
                R.layout.spinner_row, textSizes1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        p_address.setAdapter(adapter1);

        cirLoginButton11.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (ppaddress.isChecked()) {
                    stockkom="Stock alert";
                }
                else {
                    stockkom="No Alert";
                }
                if (votar.isChecked()) {
                    vatadded="Tax alert";
                }
                else {
                    vatadded="No Tax";
                }
                if (TextUtils.isEmpty(somitiname.getText().toString())||TextUtils.isEmpty(sovapoti.getText().toString())||
                        TextUtils.isEmpty(sovapoti_english.getText().toString())||TextUtils.isEmpty(fatherba.getText().toString())||
                TextUtils.isEmpty(fatheren.getText().toString())|| flag==2||TextUtils.isEmpty(valueFromSpinner)||TextUtils.isEmpty(valueFromSpinner1)
                ||TextUtils.isEmpty(natii.getText().toString())||TextUtils.isEmpty(b_date.getText().toString())||TextUtils.isEmpty(s_kisti.getText().toString())||TextUtils.isEmpty(ocupa.getText().toString())
                       ) {
                    Toasty.error(getApplicationContext(),"সমস্ত তথ্য লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    if (valueFromSpinner.equals("পণ্য বিভাগ নির্বাচন করুন")) {
                        Toasty.info(getApplicationContext(),"পণ্য বিভাগ নির্বাচন করুন",Toasty.LENGTH_SHORT,true).show();
                        return;
                    }
                    else if(valueFromSpinner1.equals("সাবকেটগরি নির্বাচন করুন")) {
                        Toasty.info(getApplicationContext(),"সাবকেটগরি নির্বাচন করুন",Toasty.LENGTH_SHORT,true).show();
                        return;
                    }
                    else if(flag==1) {
                        Toasty.info(getApplicationContext(),"পণ্য চিত্র নির্বাচন করুন",Toasty.LENGTH_SHORT,true).show();
                        return;
                    }
                    else if(flag==2) {
                        uploadImage();

                    }

                }
            }
        });


    }

    String vatadded;
    String stockkom;
    int flag=1;
    ImageView imagee;
    EditText sovapoti_english,fatheren,fatherba,natii;
    CheckBox ppaddress,votar;
    Button cirLoginButton11;


    TextView changeProfilePhoto;
    ImageButton image_button;
    ImageView imageView;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath,second,vechileimage,vechilelicesse;//Firebase

    Button floatingActionButton;
    FirebaseStorage storage;
    StorageReference storageReference;
    private static final int CAMERA_REQUEST = 1888;
    Button generate_btn;
    //doctor
    private static final int READCODE = 1;
    private static final int WRITECODE = 2;

    private Uri mainImageUri = null;
    ImageView userimage;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MemoHome.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),MemoHome.class));
        return true;
    }

    public void barcode(View view) {
        IntentIntegrator intentIntegrator=new IntentIntegrator(AddProducts.this);
        intentIntegrator.setPrompt("For  falsh use volum key.");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult.getContents()!=null) {
            AlertDialog.Builder builder=new AlertDialog.Builder(AddProducts.this);
            builder.setTitle("Result")
                    .setMessage(intentResult.getContents())
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            builder.show();
        }
        else  if (requestCode == 21 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                // Toast.makeText(this, ""+PICK_IMAGE_REQUEST, Toast.LENGTH_SHORT).show();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), second);
                imagee.setImageBitmap(bitmap);
                flag=2;

                // nid.setImageBitmap(bitmap1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toasty.error(getApplicationContext(),"Opps...did not find a qr code",Toasty.LENGTH_SHORT,true).show();

        }
    }

    Button cirLoginButton;
    EditText somitiname,sovapoti,signeture,pasword,refer,ocupa,s_kisti,
            b_date;
    Spinner mother,p_address;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
     if (parent.getId()==R.id.p_address) {
         valueFromSpinner1 = parent.getItemAtPosition(position).toString();
     }
        if (parent.getId() == R.id.mother) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.equals("পণ্য বিভাগ নির্বাচন করুন")) {
                p_address.setOnItemSelectedListener(this);

                String[] textSizes1 = getResources().getStringArray(R.array.subcos);
                ArrayAdapter adapter1 = new ArrayAdapter(this,
                        R.layout.spinner_row, textSizes1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                p_address.setAdapter(adapter1);
            }
            //1
            else  if (valueFromSpinner.equals("কস্মেটিক্স")) {
                p_address.setOnItemSelectedListener(this);

                String[] textSizes1 = getResources().getStringArray(R.array.subcos);
                ArrayAdapter adapter1 = new ArrayAdapter(this,
                        R.layout.spinner_row, textSizes1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                p_address.setAdapter(adapter1);
            }//2
            else  if (valueFromSpinner.equals("ভেষজ")) {
                p_address.setOnItemSelectedListener(this);

                String[] textSizes1 = getResources().getStringArray(R.array.subveg);
                ArrayAdapter adapter1 = new ArrayAdapter(this,
                        R.layout.spinner_row, textSizes1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                p_address.setAdapter(adapter1);
            }//3
             else  if (valueFromSpinner.equals("পোশাক")) {
                p_address.setOnItemSelectedListener(this);

                String[] textSizes1 = getResources().getStringArray(R.array.subposhak);
                ArrayAdapter adapter1 = new ArrayAdapter(this,
                        R.layout.spinner_row, textSizes1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                p_address.setAdapter(adapter1);
            }


        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    ///
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("আপলোড করা হচ্ছে...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            StorageReference ref = storageReference.child("ProfileImage/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful());
                            final Uri downloadUri=uriTask.getResult();



                            if (uriTask.isSuccessful()) {


                                ZoneId z = ZoneId.of( "America/Montreal" );
                                LocalDate today = LocalDate.now( z );
                                LocalDate oneMonthLater = today.plusMonths( 1 );
                                Long tsLong = System.currentTimeMillis()/1000;
                                String  ts = tsLong.toString();
                                Map<String, Object> user = new HashMap<>();
                                user.put("first", fatherba.getText().toString());
                                firebaseFirestore.collection("ProductsList")
                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                        .collection("New")
                                        .document(""+today)
                                        .set(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });

                                ProductAddmodel productAddmodel=new ProductAddmodel(somitiname.getText().toString(),sovapoti.getText().toString(),
                                        sovapoti_english.getText().toString(),fatherba.getText().toString(),fatheren.getText().toString(),valueFromSpinner,valueFromSpinner1,stockkom,vatadded,
                                        natii.getText().toString(), b_date.getText().toString(), s_kisti.getText().toString(), ocupa.getText().toString(),downloadUri.toString(),""+today,ts,firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid() );


                                firebaseFirestore.collection("ProductsList")
                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                        .collection("List")
                                        .document(somitiname.getText().toString().toLowerCase().toString())
                                        .set(productAddmodel)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    progressDialog.dismiss();
                                                    Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                                    startActivity(new Intent(getApplicationContext(),MemoHome.class));
                                                }
                                            }
                                        });


                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "ব্যর্থ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddProducts.this, "ব্যর্থ "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("আপলোড করা হয়েছে "+(int)progress+"%");
                        }
                    });
        }
        else {
            ZoneId z = ZoneId.of( "America/Montreal" );
            LocalDate today = LocalDate.now( z );
            LocalDate oneMonthLater = today.plusMonths( 1 );
            Long tsLong = System.currentTimeMillis()/1000;
            String  ts = tsLong.toString();
            String image="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
            ProductAddmodel productAddmodel=new ProductAddmodel(somitiname.getText().toString(),sovapoti.getText().toString(),
                    sovapoti_english.getText().toString(),fatherba.getText().toString(),fatheren.getText().toString(),valueFromSpinner,valueFromSpinner1,stockkom,vatadded,
                    natii.getText().toString(), b_date.getText().toString(), s_kisti.getText().toString(), ocupa.getText().toString(),image.toString(),""+today,ts,firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid() );


            Map<String, Object> user = new HashMap<>();
            user.put("first", fatherba.getText().toString());
            firebaseFirestore.collection("ProductsList")
                    .document(firebaseAuth.getCurrentUser().getEmail())
                    .collection("New")
                    .document(""+today)
                    .set(productAddmodel)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
            firebaseFirestore.collection("ProductsList")
                    .document(firebaseAuth.getCurrentUser().getEmail())
                    .collection("List")
                    .document(somitiname.getText().toString().toLowerCase().toString())
                    .set(productAddmodel)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toasty.success(getApplicationContext(),"সম্পন্ন হয়েছে",Toasty.LENGTH_SHORT,true).show();
                                startActivity(new Intent(getApplicationContext(),MemoHome.class));
                            }
                        }
                    });
        }
    }
}