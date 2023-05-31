package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

import static com.airbnb.lottie.L.TAG;

public class Registered extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ImageView setup_image;
    TextView changeProfilePhoto;
    String phone_numebr;
    EditText username,fullname,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Setup User");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        try {
            phone_numebr=getIntent().getStringExtra("number");
        }catch (Exception e) {
            phone_numebr=getIntent().getStringExtra("number");
        }
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        setup_image=findViewById(R.id.setup_image);
        changeProfilePhoto=findViewById(R.id.changeProfilePhoto);
        changeProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        setup_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
//widget
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog= KProgressHUD.create(Registered.this);
        username=findViewById(R.id.username);
        location=findViewById(R.id.location);
        fullname=findViewById(R.id.fullname);
        username.setText(phone_numebr);
        Button cirLoginButton=findViewById(R.id .cirLoginButton);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamemain=username.getText().toString();
                String fullnamemain=fullname.getText().toString();
                String locationmain=location.getText().toString();
                if (TextUtils.isEmpty(usernamemain)||TextUtils.isEmpty(fullnamemain)||
                        TextUtils.isEmpty(locationmain)) {
                    Toasty.error(getApplicationContext(),"Enter all information",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    if (flag==1) {
                        Toasty.info(getApplicationContext(),"Select image",Toasty.LENGTH_SHORT,true).show();
                        return;
                    }
                    else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Registered.this);
                        builder.setTitle("Confirmation")
                                .setMessage("Do you want to register in our apps?")
                                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                progress_check();
                                firebaseFirestore.collection("BlockList")
                                        .document(username.getText().toString().toLowerCase() + "@gmail.com")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (task.getResult().exists()) {
                                                        progressDialog.dismiss();
                                                        Toasty.error(getApplicationContext(), "You  are in block list.", Toast.LENGTH_SHORT, true).show();
                                                    }
                                                    else {

                                                        firebaseFirestore.collection("Name_Exsiting")
                                                                .document(username.getText().toString().toLowerCase())
                                                                .get()
                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                        if (task.isSuccessful()) {
                                                                            if (task.getResult().exists()) {
                                                                                progressDialog.dismiss();
                                                                                Toasty.error(getApplicationContext(), "This phone number already taken..", Toast.LENGTH_SHORT, true).show();
                                                                                return;
                                                                            }
                                                                            else {
                                                                                final EditText input = new EditText(Registered.this);
                                                                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                                                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                                                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                                                                input.setLayoutParams(lp);
                                                                                input.setText("amia");

                                                                                input.setHint("Enter Inviting Username");

                                                                                firebaseFirestore.collection("Old_User")
                                                                                        .document(input.getText().toString().toLowerCase())
                                                                                        .get()
                                                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                if (task.isSuccessful()) {
                                                                                                    if (task.getResult().exists()) {
                                                                                                        //Toast.makeText(RegistrationActivity.this, "make", Toast.LENGTH_SHORT).show();
                                                                                                        firebaseAuth.createUserWithEmailAndPassword(username.getText().toString().toLowerCase() + "@gmail.com", "123456")
                                                                                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                                                    @Override
                                                                                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                                                                                        if (task.isSuccessful()) {
                                                                                                                            final Map<String, String> userMap19 = new HashMap<>();
                                                                                                                            userMap19.put("refername", input.getText().toString().toLowerCase());
                                                                                                                            userMap19.put("refername_email", input.getText().toString().toLowerCase() + "@gmail.com");
                                                                                                                            userMap19.put("user_id", firebaseAuth.getCurrentUser().getUid());
                                                                                                                            userMap19.put("user_name", username.getText().toString().toLowerCase());
                                                                                                                            firebaseFirestore.collection("ALL_GET")
                                                                                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                                                    .set(userMap19)
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });

                                                                                                                            Name_Exsiting name_exsiting = new Name_Exsiting(username.getText().toString().toLowerCase(),
                                                                                                                                    username.getText().toString());
                                                                                                                            firebaseFirestore.collection("Name_Exsiting")
                                                                                                                                    .document(username.getText().toString().toLowerCase())
                                                                                                                                    .set(name_exsiting)
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                old_user(username.getText().toString().toLowerCase());
                                                                                                                                                sendStatus(firebaseAuth.getCurrentUser().getEmail());
                                                                                                                                                cris(firebaseAuth.getCurrentUser().getEmail());
                                                                                                                                                String im="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
                                                                                                                                                month_counting(firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                                                                                                                                                go_toCount(firebaseAuth.getCurrentUser().getEmail(),firebaseAuth.getCurrentUser().getUid());
                                                                                                                                                String name=fullname.getText().toString();
                                                                                                                                                //email=edtemail.getText().toString();
                                                                                                                                                String password=username.getText().toString();
                                                                                                                                                String mobile=username.getText().toString();
                                                                                                                                                Long tsLong = System.currentTimeMillis()/1000;
                                                                                                                                                String ts = tsLong.toString();

                                                                                                                                                String image22="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
                                                                                                                                                storeToFirestore1(fullnamemain, "abc@gmail.com",image22, usernamemain);

                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });

                                                                                                                        }
                                                                                                                    }
                                                                                                                });
                                                                                                    }
                                                                                                    else {
                                                                                                        progressDialog.dismiss();
                                                                                                        Toasty.error(getApplicationContext(), "No User Found .", Toast.LENGTH_SHORT, true).show();
                                                                                                        return;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        });



                                                                            }
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                }
                                            }
                                        });
                            }
                        }).create();
                        builder.show();
                    }
                }
            }
        });
    }
    private void storeToFirestore1(final String name, final String toString, String image22, final String email) {
        Log.d(TAG+"_TXN","8. SAVING DATA TO FIRESTORE");
        String im="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
        // Storing data on Firestore...

        final Map<String, String> userMap = new HashMap<>();
        userMap.put("name",name);
        userMap.put("image", im);
        userMap.put("number", username.getText().toString());
        userMap.put("email",username.getText().toString().toLowerCase());
        userMap.put("pass",username.getText().toString().toLowerCase());
        userMap.put("username", username.getText().toString().toLowerCase());
        userMap.put("country",username.getText().toString());
        userMap.put("uuid",firebaseAuth.getCurrentUser().getUid());


        final Map<String, String> userMap2 = new HashMap<>();
        userMap2.put("counter","0");
        firebaseFirestore.collection("RatingCounter")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .set(userMap2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

        firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid()).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if(task.isSuccessful())
                {

                    // Sending Verification Link via Email
                    Log.d(TAG+"_TXN","9. DATA SAVED");
                    Log.d(TAG+"_TXN","10. SENDING EMAIL");
                    firebaseFirestore.collection("User2").document(firebaseAuth.getCurrentUser().getEmail())
                            .set(userMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        User_coun user_coun=new User_coun( name,toString,email,"0");
                                        firebaseFirestore.collection("Users")
                                                .document(firebaseAuth.getCurrentUser().getUid()).collection("Coins")
                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                .set(user_coun)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            MainCoin mainCoin=new MainCoin("0");
                                                            firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid())
                                                                    .collection("MainCoin")
                                                                    .document(firebaseAuth.getCurrentUser().getEmail().toLowerCase())
                                                                    .set(mainCoin)
                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                            if (task.isSuccessful()) {
                                                                                Map<String, String> userMap1 = new HashMap<>();

                                                                                userMap1.put("username", username.getText().toString().toLowerCase());
                                                                                firebaseFirestore.collection("Old_User")
                                                                                        .document(username.getText().toString().toLowerCase())
                                                                                        .set(userMap1)
                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                if (task.isSuccessful()) {
                                                                                                    Map<String, String> userMap7 = new HashMap<>();

                                                                                                    userMap7.put("uuid",firebaseAuth.getCurrentUser().getUid());
                                                                                                    firebaseFirestore.collection("All_UserID")
                                                                                                            .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                            .set(userMap7)
                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                @Override
                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                    if (task.isSuccessful()) {

                                                                                                                        firebaseFirestore.collection("Users")
                                                                                                                                .document(firebaseAuth.getCurrentUser().getUid())
                                                                                                                                .update("image", im)
                                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                    @Override
                                                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                        if (task.isSuccessful()) {

                                                                                                                                            Map<String, String> paSS = new HashMap<>();

                                                                                                                                            paSS.put("uuid",username.getText().toString().toLowerCase());
                                                                                                                                            firebaseFirestore.collection("Password")
                                                                                                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                                                                    .set(paSS)
                                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                                if(filePath != null)
                                                                                                                                                                {
                                                                                                                                                                    final ProgressDialog progressDialog = new ProgressDialog(Registered.this);
                                                                                                                                                                    progressDialog.setTitle("Uploading...");
                                                                                                                                                                    progressDialog.setCancelable(false);


                                                                                                                                                                    StorageReference ref = storageReference.child("Doctor_images/"+ UUID.randomUUID().toString());
                                                                                                                                                                    ref.putFile(filePath)
                                                                                                                                                                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                                                                                                                                    Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                                                                                                                                                                                    while (!uriTask.isSuccessful());
                                                                                                                                                                                    final Uri downloadUri=uriTask.getResult();



                                                                                                                                                                                    if (uriTask.isSuccessful()) {
                                                                                                                                                                                        firebaseFirestore.collection("User2")
                                                                                                                                                                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                                                                                                                .update("image",downloadUri.toString())
                                                                                                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                                                                                                                                                    }
                                                                                                                                                                                                });
                                                                                                                                                                                        firebaseFirestore.collection("Users")
                                                                                                                                                                                                .document(firebaseAuth.getCurrentUser().getUid())
                                                                                                                                                                                                .update("image",downloadUri.toString())
                                                                                                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                                        if (task.isSuccessful()) {

                                                                                                                                                                                                            Map<String, String> paSS = new HashMap<>();

                                                                                                                                                                                                            paSS.put("uuid",username.getText().toString().toLowerCase());
                                                                                                                                                                                                            firebaseFirestore.collection("Password")
                                                                                                                                                                                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                                                                                                                                                                                    .set(paSS)
                                                                                                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                                                                                                //firebaseAuth.signOut();
                                                                                                                                                                                                                                progressDialog.dismiss();
                                                                                                                                                                                                                                Handler handler=new Handler();
                                                                                                                                                                                                                                handler.postDelayed(new Runnable() {
                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                    public void run() {
                                                                                                                                                                                                                                        firebaseAuth.signOut();
                                                                                                                                                                                                                                        AlertDialog.Builder builder=new AlertDialog.Builder(Registered.this);
                                                                                                                                                                                                                                        builder.setTitle("Confirmation")
                                                                                                                                                                                                                                                .setMessage("You are successfully register in Go Bandarbon.\nYour Phone number : "+username.getText().toString()+"\n")
                                                                                                                                                                                                                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                                                                                                                                                                                                        dialog.dismiss();
                                                                                                                                                                                                                                                        Toasty.success(getApplicationContext(), "Account setup  Successfully Done.", Toasty.LENGTH_SHORT, true).show();

                                                                                                                                                                                                                                                        Intent loginSuccess = new Intent(Registered.this,LoginActivity.class);

                                                                                                                                                                                                                                                        startActivity(loginSuccess);
                                                                                                                                                                                                                                                        finish();
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }).create();
                                                                                                                                                                                                                                        builder.show();


                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                },1000);


                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    });
                                                                                                                                                                                                            // startActivity(new Intent(getApplicationContext(),HomeActivity.class));


                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                });




                                                                                                                                                                                    }
                                                                                                                                                                                    else {
                                                                                                                                                                                        progressDialog.dismiss();
                                                                                                                                                                                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                                                                                                                                                                    }

                                                                                                                                                                                }
                                                                                                                                                                            })
                                                                                                                                                                            .addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                    progressDialog.dismiss();
                                                                                                                                                                                    Toast.makeText(Registered.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                }
                                                                                                                                                                            })
                                                                                                                                                                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                                                                                                                                                                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                                                                                                                                                                            .getTotalByteCount());
                                                                                                                                                                                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
                                                                                                                                                                                }
                                                                                                                                                                            });
                                                                                                                                                                }


                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    });
                                                                                                                                            // startActivity(new Intent(getApplicationContext(),HomeActivity.class));


                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                });





                                                                                                                    }
                                                                                                                }
                                                                                                            });

                                                                                                }
                                                                                            }
                                                                                        });
                                                                            }
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                }
                            });





                } else {
                    Log.d(TAG+"_ERR5","ERROR IN SAVING DATA");
                    progressDialog.dismiss();
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(Registered.this,"FIRESTORE Error : "+errorMessage,Toast.LENGTH_LONG).show();

                }

            }
        });

    }
    private void old_user(String toString) {
        final Map<String, String> userMap = new HashMap<>();
        userMap.put("username",toString);
        firebaseFirestore.collection("Old_User")
                .document(toString.toLowerCase())
                .set(userMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                        }
                    }
                });

    }
    private void go_toCount(String email, String uid) {
        firebaseFirestore.collection("Users").document(uid)
                .collection("Counter")
                .document(email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            if (task.getResult().exists()) {


                            }
                            else {

                                Map<String, String> userMap1 = new HashMap<>();

                                userMap1.put("counter","1");
                                firebaseFirestore.collection("Users").document(uid)
                                        .collection("Counter")
                                        .document(email)
                                        .set(userMap1)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Map<String, String> userMap2 = new HashMap<>();

                                                    userMap2.put("main_balance","0");
                                                    userMap2.put("purches_balance","0");
                                                    userMap2.put("giving_balance","0");
                                                    userMap2.put("self_income","0");
                                                    userMap2.put("sponsor_income","0");
                                                    userMap2.put("first_level","0");
                                                    userMap2.put("second_level","0");
                                                    userMap2.put("third_level","0");
                                                    userMap2.put("forth_level","0");
                                                    userMap2.put("fifth_level","0");
                                                    userMap2.put("shoping_balance","0");
                                                    userMap2.put("cashwalet","0");
                                                    userMap2.put("monthly_income","0");
                                                    userMap2.put("leader_club","0");
                                                    userMap2.put("gen_bonus","0");
                                                    userMap2.put("ref_bonus","0");
                                                    userMap2.put("daily_income","0");

                                                    firebaseFirestore.collection("Users").document(uid)
                                                            .collection("Main_Balance")
                                                            .document( email)
                                                            .set(userMap2)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Map<String, String> userMap3 = new HashMap<>();

                                                                        userMap3.put("rating","0");
                                                                        firebaseFirestore.collection("Users").document(uid)
                                                                                .collection("Rating")
                                                                                .document( email)
                                                                                .set(userMap3)
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            Map<String, String> userMap4 = new HashMap<>();

                                                                                            userMap4.put("package","Inactive");
                                                                                            firebaseFirestore.collection("Users").document(uid)
                                                                                                    .collection("Package")
                                                                                                    .document( email)
                                                                                                    .set(userMap4)
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                            if (task.isSuccessful()) {
                                                                                                                Map<String, String> userMap5= new HashMap<>();

                                                                                                                userMap5.put("ammount","0");
                                                                                                                firebaseFirestore.collection("SendMoney").document(email)
                                                                                                                        .set(userMap5)
                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                if (task.isSuccessful()) {
                                                                                                                                }
                                                                                                                            }
                                                                                                                        });
                                                                                                            }
                                                                                                        }
                                                                                                    });
                                                                                        }
                                                                                    }
                                                                                });


                                                                    }
                                                                }
                                                            });


                                                }
                                            }
                                        });



                            }////
                        }
                    }
                });
    }

    private void cris(String email) {
        Map<String, String> userMap1 = new HashMap<>();

        userMap1.put("get", "0");
        firebaseFirestore.collection("Firest")
                .document(email)
                .set(userMap1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                        }
                    }
                });
    }
    private void sendStatus(String emailll) {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month2=month+1;
        String dadda=day+"/"+month2+"/"+year;
        final Map<String, String> userMap191 = new HashMap<>();

        userMap191.put("opining", dadda);
        firebaseFirestore.collection("Send_Status")
                .document(emailll)
                .set(userMap191)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }
    private void month_counting(String email, String uid) {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        Map<String, String> userMap1 = new HashMap<>();

        userMap1.put("counter","0");
        userMap1.put("month",""+month);
        firebaseFirestore.collection("Month_counter")
                .document(email)
                .set(userMap1)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                        }
                    }
                });

    }
    KProgressHUD progressDialog;
    private void progress_check() {
        progressDialog = KProgressHUD.create(Registered.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Setup User....")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                setup_image.setImageBitmap(bitmap);
                flag=2;


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    int flag=1;

    ImageButton image_button;
    ImageView imageView;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath,second,third,vechileimage,vechilelicesse;//Firebase

    Button floatingActionButton;
    FirebaseStorage storage;
    StorageReference storageReference;
    private static final int CAMERA_REQUEST = 1888;
    Button generate_btn;
    //doctor
    private static final int READCODE = 1;
    private static final int WRITECODE = 2;

    private Uri mainImageUri = null;
}