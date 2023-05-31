package com.meass.calculator_apps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.myview> {
    public List<ContactModel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String detecor,gettingtext;

    public ContactAdapter(List<ContactModel> data) {
        this.data = data;
        this.detecor =detecor;
                this.gettingtext = gettingtext;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gggggg,parent,false);
        return new myview(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        holder.lastkisti.setText(" নাম: "+data.get(position).getName());
        holder.kinadam.setText("ফোন নম্বর : "+data.get(position).getNumber());
        holder.logout.setText(gettingtext);
        holder.card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bb=new AlertDialog.Builder(v.getContext());
                bb.setTitle("নিশ্চিতকরণ")
                        .setMessage("আপনি এটা মুছে দিতে চান")
                        .setPositiveButton("না", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setNegativeButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        final KProgressHUD progressDialog=  KProgressHUD.create(v.getContext())
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                .setLabel("ডেটা মুছে ফেলা হচ্ছে.....")
                                .setCancellable(false)
                                .setAnimationSpeed(2)
                                .setDimAmount(0.5f)
                                .show();
                        firebaseFirestore.collection("ContactList")
                                .document(firebaseAuth.getCurrentUser().getEmail())
                                .collection("List")
                                .document(""+data.get(position).getTime().toString().toLowerCase())
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            if (task.getResult().exists()) {
                                                firebaseFirestore.collection("ContactList")
                                                        .document(firebaseAuth.getCurrentUser().getEmail())
                                                        .collection("List")
                                                        .document(""+data.get(position).getTime().toString().toLowerCase()).delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    progressDialog.dismiss();
                                                                    v.getContext().startActivity(new Intent(v.getContext(),Sonchoyhome.class));
                                                                }
                                                            }
                                                        });

                                            }
                                        }
                                    }
                                });

                    }
                }).create();
                bb.show();

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


