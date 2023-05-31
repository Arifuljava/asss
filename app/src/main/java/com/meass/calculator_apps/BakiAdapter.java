package com.meass.calculator_apps;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class BakiAdapter extends RecyclerView.Adapter<BakiAdapter.myview> {
    public List<BakiMainModel> data;
    FirebaseFirestore firebaseFirestore;

    public BakiAdapter(List<BakiMainModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public BakiAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bakihome, parent, false);
        return new BakiAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BakiAdapter.myview holder, final int position) {
        holder.card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag==1) {
                    holder.idsecondlayout.setVisibility(View.VISIBLE);
                    holder.firstlay.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.kistiback));
                    flag=2;
                }
                else if(flag==2) {
                    holder.idsecondlayout.setVisibility(View.GONE);
                    holder.firstlay.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.fii));
                    flag=1;
                }
            }
        });
        holder.dailyCheckCard.setText(data.get(position).getC_name());
        holder.taskCard6.setText(data.get(position).getBakidibe());
        holder.namee.setText(data.get(position).getC_name());
        holder.jomafirst.setText(data.get(position).getC_name());
        holder.kinadam.setText(data.get(position).getC_number());
        holder.lastkisti.setText(data.get(position).getJoma());
        holder.bakikisti.setText(data.get(position).getBakidibe());
        holder.datetarik.setText(data.get(position).getDate());

    }
    int flag=1;

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
      LinearLayout firstlay,idsecondlayout;
      TextView dailyCheckCard,taskCard,taskCard6;
      Button namee,jomafirst,lastkisti,bakikisti,datetarik,kinadam;
      CardView card_view8;

        public myview(@NonNull View itemView) {
            super(itemView);
            dailyCheckCard=itemView.findViewById(R.id.dailyCheckCard);
            taskCard=itemView.findViewById(R.id.taskCard);
            taskCard6=itemView.findViewById(R.id.taskCard6);
            kinadam=itemView.findViewById(R.id.kinadam);
            //
            namee=itemView.findViewById(R.id.namee);
            jomafirst=itemView.findViewById(R.id.jomafirst);
            lastkisti=itemView.findViewById(R.id.lastkisti);
            bakikisti=itemView.findViewById(R.id.bakikisti);
            datetarik=itemView.findViewById(R.id.datetarik);



            firstlay=itemView.findViewById(R.id.firstlay);
            idsecondlayout=itemView.findViewById(R.id.idsecondlayout);
            card_view8=itemView.findViewById(R.id.card_view8);


        }
    }
}