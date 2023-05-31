package com.meass.calculator_apps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.owater.library.CircleTextView;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class HomeACTIVITY extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();


    private HashMap<String, String> user;
    private String name, email, photo, mobile,username;
    private Drawer result;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;
    FirebaseFirestore db;

    FirebaseUser firebaseUser;
    String user1;

    IProfile profile;
    TextView nameTv,emailTv;
    ImageView profileImage;
    TextView coinsT1v,points2;
    CardView dailyCheckCard,luckySpinCard,aboutCard1,aboutCard,redeemCard,referCard,taskCard;
    FirebaseFirestore firebaseFirestore;
    String sessionname,sessionmobile,sessionphoto,sessionemail,sessionusername;
    int count,count1,count2,count3;
    String package_actove;
    String daily_bonus;
    String incomeType="Daily Task";
    int main_account;
    int count12,count123;
    int main_refer;
    UserSession session;
    Context ctx;
    Dialog mDialog;
    //
    ArrayAdapter adapter;
    ArrayList<String> list = new ArrayList<>();
    private  String tyepe,active,nae="Watch Video";

    KProgressHUD kProgressHUD;
    CircleTextView cir;
    TextView maintokiyo;
    WebView videoView;
    int third,main_invest;
    String detector;
    //
    String main_task ;



    private TextView tvemail,tvphone;
    private HashMap<String,String> uaser;


    FirebaseFirestore db1;
    private SliderView imageSlider,imageSlider111;
    FirebaseFirestore firebaseFirestore1,firebaseFirestore11;
    //
    private static final int REQUEST_LOCATION = 1;
    Button btnGetLocation;
    TextView showLocation;
    LocationManager locationManager;
    String c_latitude, c_longitude;
   /// private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    FirebaseAuth firebaseAcuth;
    FirebaseUser firebasecUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_a_c_t_i_v_i_t_y);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(HomeACTIVITY.this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseFirestore=FirebaseFirestore.getInstance();
        session=new UserSession(getApplicationContext());

        session = new UserSession(getApplicationContext());
        getValues();
        getValues();
        inflateNavDrawer();
        //Toast.makeText(HomeACTIVITY.this, ""+1685%1000, Toast.LENGTH_SHORT).show();


///
        dailyCheckCard=findViewById(R.id.dailyCheckCard);
       // dailyCheckCard.setAnimation(topAnimation);

        luckySpinCard=findViewById(R.id.luckySpinCard);
     //   luckySpinCard.setAnimation(endAnimation);

        taskCard=findViewById(R.id.taskCard);
       // taskCard.setAnimation(startAnimation);

       CardView referCardq=findViewById(R.id.referCardq);
     //   referCardq.setAnimation(topAnimation);

        CardView  watccchCard=findViewById(R.id.watccchCard);
      //  watccchCard.setAnimation(startAnimation);

        CardView recccdeemCard=findViewById(R.id.recccdeemCard);
      //  recccdeemCard.setAnimation(startAnimation);

        referCard=findViewById(R.id.referCard);
       // referCard.setAnimation(topAnimation);

        CardView watchCard=findViewById(R.id.watchCard);
      //  watchCard.setAnimation(endAnimation);

        redeemCard=findViewById(R.id.redeemCard);
      //  redeemCard.setAnimation(startAnimation);

       // dailyCheckCard=findViewById(R.id.dailyCheckCard);
        //dailyCheckCard.setAnimation(bottomAnimation);


    }

    private void getValues() {
        //validating session
        session.isLoggedIn();

        //get User details if logged in
        user = session.getUserDetails();

        name = user.get(UserSession.KEY_NAME);
        email = user.get(UserSession.KEY_EMAIL);
        mobile = user.get(UserSession.KEY_MOBiLE);
        // Toast.makeText(this, ""+mobile, Toast.LENGTH_SHORT).show();
        photo = user.get(UserSession.KEY_PHOTO);
        username=user.get(UserSession.Username);
        //Toast.makeText(this, ""+username, Toast.LENGTH_SHORT).show();

        /// Toast.makeText(getApplicationContext(), ""+photo, Toast.LENGTH_SHORT).show();
    }
    private void inflateNavDrawer() {

        //set Custom toolbar to activity -----------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the AccountHeader ----------------------------------------------------------------

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.get().load(uri).placeholder(placeholder).into(imageView);
            }
            @Override
            public void cancel(ImageView imageView) {
                Picasso.get().cancelRequest(imageView);
            }
        });


        //Profile Making
       /*
        IProfile profile = new ProfileDrawerItem()
                .withName(name)
                .withEmail(email)
                .withIcon(photo);
        */
        String image22="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";

        //String image22="https://firebasestorage.googleapis.com/v0/b/mydex-91780.appspot.com/o/profile_images%2FNt4rITrPbqc9e1AHPKPaLb4IcFI3.jpg?alt=media&token=d2da7b65-ed6a-41c9-8d1c-ade16adddc3a";
        if (photo.contains(image22)) {
            profile = new ProfileDrawerItem()
                    .withName(name)
                    .withEmail(email)
                    .withIcon(R.drawable.businessman);

        }
        else {
            profile = new ProfileDrawerItem()
                    .withName(name)
                    .withEmail(email)
                    .withIcon(photo);
        }

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.gradient_background)
                .addProfiles(profile)
                .withCompactStyle(true)
                .build();

        //Adding nav drawer items ------------------------------------------------------------------
        //Adding nav drawer items ------------------------------------------------------------------
        //Adding nav drawer items ------------------------------------------------------------------
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.home).withIcon(R.drawable.home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.myprofile).withIcon(R.drawable.profile);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.logout).withIcon(R.drawable.logout);




        //PrimaryDrawerItem item14 = new PrimaryDrawerItem().withIdentifier(15).withName("Privecy Policey").withIcon(R.drawable.ic_baseline_policy_24);
        //creating navbar and adding to the toolbar ------------------------------------------------
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withDrawerLayout(R.layout.crossfade_drawer)
                .withAccountHeader(headerResult)
                .withDrawerWidthDp(72)
                .withGenerateMiniDrawer(true)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(item1,item2,item5, new DividerDrawerItem(), new DividerDrawerItem()).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch (position){

                            case 1:
                                if (result != null && result.isDrawerOpen()) {
                                    result.closeDrawer();
                                }
                                break;
                            case 2:
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                                break;

                            case 3:
                                AlertDialog.Builder warning = new AlertDialog.Builder(HomeACTIVITY.this)
                                        .setTitle("Logout")
                                        .setMessage("Do you want to logout?")
                                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();



                                            }
                                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // ToDO: delete all the notes created by the Anon user

                                                // TODO: delete the anon user
                                                firebaseAuth.signOut();
                                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                finish();


                                            }
                                        });

                                warning.show();

                                result.closeDrawer();
                                break;


                            case 10:
                                final FlatDialog flatDialog = new FlatDialog(HomeACTIVITY.this);
                                flatDialog.setCancelable(false);
                                flatDialog.setTitle("Send a message")

                                        .setTitleColor(Color.parseColor("#000000"))
                                        .setBackgroundColor(Color.parseColor("#f5f0e3"))
                                        .setLargeTextFieldHint("write your text here ...")
                                        .setLargeTextFieldHintColor(Color.parseColor("#000000"))
                                        .setLargeTextFieldBorderColor(Color.parseColor("#000000"))
                                        .setLargeTextFieldTextColor(Color.parseColor("#000000"))
                                        .setFirstButtonColor(Color.parseColor("#fda77f"))
                                        .setFirstButtonTextColor(Color.parseColor("#000000"))
                                        .setFirstButtonText("Done")

                                        .  setSecondButtonText("Cancel")
                                        .withFirstButtonListner(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                flatDialog.dismiss();
                                                String uuid= UUID.randomUUID().toString();
                                                Map<String, String> userMap41 = new HashMap<>();
                                                userMap41.put("username",username);
                                                userMap41.put("uuid",uuid);

                                                userMap41.put("feedback", flatDialog.getLargeTextField().toLowerCase());
                                                firebaseFirestore=FirebaseFirestore.getInstance();
                                                firebaseFirestore.collection("Customer_feedback")
                                                        .document(uuid)
                                                        .set(userMap41)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toasty.success(getApplicationContext(),"Send",Toasty.LENGTH_SHORT,true).show();
                                                                }
                                                            }
                                                        });


                                            }
                                        })
                                        .withSecondButtonListner(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                flatDialog.dismiss();
                                            }
                                        })
                                        .show();
                                result.closeDrawer();
                                break;





                            //tapview();

                            default:
                                Toast.makeText(HomeACTIVITY.this, "Default", Toast.LENGTH_LONG).show();
                        }

                        return true;
                    }
                }).build();



        //Setting crossfader drawer------------------------------------------------------------

        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));

        //add second view (which is the miniDrawer)
        final MiniDrawer miniResult = result.getMiniDrawer();

        //build the view for the MiniDrawer
        View view = miniResult.build(this);

        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));

        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });
    }
    @Override
    public void onBackPressed() {
        final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(HomeACTIVITY.this)
                .setBackgroundColor(R.color.white)
                .setTextTitle("Exit")
                .setCancelable(false)
                .setTextSubTitle("Are you want to exit")
                .setBody("User is not stay at app when user click exit button.")
                .setPositiveButtonText("No")
                .setPositiveColor(R.color.toolbar)
                .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();

                    }
                })
                .setNegativeButtonText("Exit")
                .setNegativeColor(R.color.colorPrimaryDark)
                .setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
                    @Override
                    public void OnClick(View view, Dialog dialog) {
                        dialog.dismiss();
                        finishAffinity();

                    }
                })
                .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                .setCancelable(false)
                .build();
        alert.show();

    }

    public void home(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeMain.class);
        startActivity(intent);
    }

    public void calculator(View view) {
        Intent intent=new Intent(getApplicationContext(),Calculatorhome.class);
        startActivity(intent);
    }

    public void due(View view) {
        Intent intent=new Intent(getApplicationContext(),BakiHome.class);
        startActivity(intent);
    }

    public void cash(View view) {
        Intent intent=new Intent(getApplicationContext(),CashHome.class);
        startActivity(intent);
    }

    public void cash_memo(View view) {
        Intent intent=new Intent(getApplicationContext(),MemoHome.class);
        startActivity(intent);
    }

    public void call(View view) {
        Intent intent=new Intent(getApplicationContext(),ContactHome.class);
        startActivity(intent);
    }

    public void savings(View view) {
        Intent intent=new Intent(getApplicationContext(),Sonchoyhome.class);
        startActivity(intent);
    }

    public void news(View view) {
        Intent intent=new Intent(getApplicationContext(),NewsPaperHome.class);
        startActivity(intent);
    }

    public void onlineservice(View view) {
        Intent intent=new Intent(getApplicationContext(),OnlineServiceHome.class);
        startActivity(intent);
    }
}