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
holder.lastkisti.setText("ক্রেতার নাম : "+data.get(position).getCus_name());

        holder.kinadam.setText("ক্যাশ  : "+data.get(position).getJoma()+" টাকা");
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


