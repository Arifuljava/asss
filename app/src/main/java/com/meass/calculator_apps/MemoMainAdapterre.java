package com.meass.calculator_apps;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.codesgood.views.JustifiedTextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MemoMainAdapterre extends RecyclerView.Adapter<MemoMainAdapterre.myview> {
    public List<MemoProductsModel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public MemoMainAdapterre(List<MemoProductsModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.productsss,parent,false);
        return new myview(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        // holder.logout.setText("৪০ ট\nপরিশোধ");
        holder.card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==1) {
                    double ddd=Double.parseDouble(data.get(position).getBaki())+Double.parseDouble(data.get(position).getNagad());

                    String message=data.get(position).getProductsname()+" "+data.get(position).getProcategory()+
                            "  : "+data.get(position).getPices().toString()+" পিস\n" +
                            "দাম      : "+ddd+" টাকা\n" +
                            "নগদ      : "+data.get(position).getNagad().toString()+" টাকা\n" +
                            "বাকি       : "+data.get(position).getBaki().toString()+" টাকা\n" +
                            "\n\n"+data.get(position).getStatus();

                    holder.idsecondlayout.setVisibility(View.VISIBLE);
                    holder.firstlay.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.kistiback));
                    holder.sikar.setText(message);
                    flag=2;
                }
                else if(flag==2) {
                    holder.idsecondlayout.setVisibility(View.GONE);
                    holder.firstlay.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.fii));
                    flag=1;
                }
            }
        });
        holder.dailyCheckCard.setText(data.get(position).getCompanyname());
        holder.taskCard.setText("বাকি");
        holder.taskCard6.setText(data.get(position).getBaki());
        holder.dailyCheckC1ard.setText(data.get(position).getKetaname());
        holder.taskCa1rd.setText("নগদ");
        holder.taskCar11d6.setText(data.get(position).getNagad());







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
        TextView dailyCheckCard,taskCard6,bakikisti,datetarik,addkisti,namee,jomafirst,taskCard,dailyCheckC1ard
                ,taskCa1rd,taskCar11d6;
        JustifiedTextView sikar;
        public myview(@NonNull View itemView) {
            super(itemView);
            dailyCheckCard=itemView.findViewById(R.id.dailyCheckCard);
            taskCard=itemView.findViewById(R.id.taskCard);
            taskCard6=itemView.findViewById(R.id.taskCard6);
            dailyCheckC1ard=itemView.findViewById(R.id.dailyCheckC1ard);
            taskCa1rd=itemView.findViewById(R.id.taskCa1rd);
            taskCar11d6=itemView.findViewById(R.id.taskCar11d6);
            idsecondlayout=itemView.findViewById(R.id.idsecondlayout);
            sikar=itemView.findViewById(R.id.sikar);
            card_view8=itemView.findViewById(R.id.card_view8);
            firstlay=itemView.findViewById(R.id.firstlay);

        }
    }
}


