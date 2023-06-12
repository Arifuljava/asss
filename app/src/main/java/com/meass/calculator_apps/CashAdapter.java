package com.meass.calculator_apps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

public class CashAdapter extends RecyclerView.Adapter<CashAdapter.myview> {
    public List<AddCashmodel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String detecor,gettingtext;

    public CashAdapter(List<AddCashmodel> data, String detecor, String gettingtext) {
        this.data = data;
        this.detecor =detecor;
                this.gettingtext = gettingtext;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.asss,parent,false);
        return new myview(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        double lav = Double.parseDouble(data.get(position).getSell_dam());
        double kena = Double.parseDouble(data.get(position).getKenadam());
        if (kena>lav) {
            double cc = kena- lav;

            holder.lastkisti.setText("ক্রেতার নাম : "+data.get(position).getCus_name());

            holder.kinadam.setText("ক্যাশ  : "+data.get(position).getJoma()+" টাকা"+"\n" +
                    "কেনা দাম :  "+kena+"\n" +
                    "বিক্রির দাম : "+lav+"\n" +
                    "ক্ষতি : "+cc);

        }
        else {
            double cc = lav- kena;
            holder.lastkisti.setText("ক্রেতার নাম : "+data.get(position).getCus_name());

            holder.kinadam.setText("ক্যাশ  : "+data.get(position).getJoma()+" টাকা"+"\n" +
                    "কেনা দাম :  "+kena+"\n" +
                    "বিক্রির দাম : "+lav+"\n" +
                    "লাভ : "+cc);

        }
        holder.card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String list[]={"ক্রেতার নাম ","বিক্রির দাম", "কেনা দাম "};
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setTitle("আপডেট অপশন").setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    if(which==2) {
                        final FlatDialog flatDialog1 = new FlatDialog(v.getContext());
                        flatDialog1.setTitle("আপডেট অপশন")
                                .setSubtitle("কেনা দাম  আপডেট")
                                .setFirstTextFieldHint("কেনা দাম")
                                .setFirstButtonText("এটি আপডেট করুন")
                                .setSecondButtonText("বাতিল করুন")
                                .withFirstButtonListner(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String tt = flatDialog1.getFirstTextField().toString();
                                        if (TextUtils.isEmpty(tt)
                                        ) {

                                            Toast.makeText(v.getContext(), "তথ্য লিখুন", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            flatDialog1.dismiss();

                                            firebaseFirestore.collection("AddToCash")
                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                    .collection("List")
                                                    .document(data.get(position).getTime())
                                                    .update("kenadam",""+tt)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(v.getContext(), "সম্পন্ন", Toast.LENGTH_SHORT).show();
                                                            }

                                                        }
                                                    });
                                        }

                                    }
                                })
                                .withSecondButtonListner(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        flatDialog1.dismiss();
                                    }
                                })
                                .show();
                    }
                    else if(which==1) {
                            final FlatDialog flatDialog1 = new FlatDialog(v.getContext());
                            flatDialog1.setTitle("আপডেট অপশন")
                                    .setSubtitle("বিক্রির দাম  আপডেট")
                                    .setFirstTextFieldHint("বিক্রির দাম")
                                    .setFirstButtonText("এটি আপডেট করুন")
                                    .setSecondButtonText("বাতিল করুন")
                                    .withFirstButtonListner(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String tt = flatDialog1.getFirstTextField().toString();
                                            if (TextUtils.isEmpty(tt)
                                            ) {

                                                Toast.makeText(v.getContext(), "তথ্য লিখুন", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                flatDialog1.dismiss();

                                                firebaseFirestore.collection("AddToCash")
                                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                                        .collection("List")
                                                        .document(data.get(position).getTime())
                                                        .update("sell_dam",""+tt)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(v.getContext(), "সম্পন্ন", Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });
                                            }

                                        }
                                    })
                                    .withSecondButtonListner(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            flatDialog1.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    else   if (which==0) {
                            final FlatDialog flatDialog1 = new FlatDialog(v.getContext());
                            flatDialog1.setTitle("আপডেট অপশন")
                                    .setSubtitle("ক্রেতার নাম আপডেট")
                                    .setFirstTextFieldHint("নাম")
                                    .setFirstButtonText("এটি আপডেট করুন")
                                    .setSecondButtonText("বাতিল করুন")
                                    .withFirstButtonListner(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            String tt = flatDialog1.getFirstTextField().toString();
                                            if (TextUtils.isEmpty(tt)
                                            ) {

                                                Toast.makeText(v.getContext(), "তথ্য লিখুন", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                flatDialog1.dismiss();

                                                firebaseFirestore.collection("AddToCash")
                                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                                        .collection("List")
                                                        .document(data.get(position).getTime())
                                                        .update("cus_name",""+tt)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(v.getContext(), "সম্পন্ন", Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });
                                            }

                                        }
                                    })
                                    .withSecondButtonListner(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            flatDialog1.dismiss();
                                        }
                                    })
                                    .show();
                        }

                    }
                }).create();
                builder.show();
            }
        });

            }
    int flag=1;

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView customer_name,customer_number,customer_area,logout,customer_area3,customer_area8;
        CardView card_view8;
        ImageView profile_image;
        LinearLayout idsecondlayout,firstlay;
        Button dailyCheckCard,taskCard6,bakikisti,datetarik,addkisti,namee,jomafirst;
        TextView ggg,kinadam,lastkisti;
        public myview(@NonNull View itemView) {
            super(itemView);
            lastkisti=itemView.findViewById(R.id.customer_name);
            kinadam=itemView.findViewById(R.id.customer_number);
            card_view8 = itemView.findViewById(R.id.card_view8);
            logout=itemView.findViewById(R.id.logout);

        }
    }
}


