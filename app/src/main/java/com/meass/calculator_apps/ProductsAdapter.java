package com.meass.calculator_apps;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.myview> {
    public List<ProductAddmodel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public ProductsAdapter(List<ProductAddmodel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.productsdetailsimage,parent,false);
        return new myview(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        String iamegs=data.get(position).getProimage();
        String image="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
        if (image.equals(iamegs)) {

        }
        else {
            try {
                Picasso.get().load(data.get(position).getProimage()).into(holder.profile_image);
            }catch (Exception e) {
                Picasso.get().load(data.get(position).getProimage()).into(holder.profile_image);
            }

        }
        // holder.logout.setText("৪০ ট\nপরিশোধ");
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
        holder.customer_name.setText(data.get(position).getProductsname());
        holder.customer_number.setText(data.get(position).getStock()+" টি");
        holder.logout.setText(data.get(position).getCategory()+"\n"+data.get(position).getSubcategory());
        holder.namee.setText(data.get(position).getProductsname());
        holder.jomafirst.setText(data.get(position).getSellprice()+" টাকা");
        holder.kinadam.setText(data.get(position).getKenadam()+" টাকা");
        holder.lastkisti.setText(data.get(position).getStock()+" টি");
        holder.bakikisti.setText(data.get(position).getDiscount()+" %");
        holder.datetarik.setText(data.get(position).getS_date()+" - "+data.get(position).getStopdate());






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
        Button dailyCheckCard,taskCard6,bakikisti,datetarik,addkisti,namee,jomafirst,kinadam,lastkisti;
        public myview(@NonNull View itemView) {
            super(itemView);
            lastkisti=itemView.findViewById(R.id.lastkisti);
            kinadam=itemView.findViewById(R.id.kinadam);
            jomafirst=itemView.findViewById(R.id.jomafirst);
            namee=itemView.findViewById(R.id.namee);
            bakikisti=itemView.findViewById(R.id.bakikisti);
            customer_name=itemView.findViewById(R.id.customer_name);
            customer_number=itemView.findViewById(R.id.customer_number);
            logout=itemView.findViewById(R.id.logout);
            card_view8=itemView.findViewById(R.id.card_view8);
            profile_image=itemView.findViewById(R.id.image);
            idsecondlayout=itemView.findViewById(R.id.idsecondlayout);
            firstlay=itemView.findViewById(R.id.firstlay);
            dailyCheckCard=itemView.findViewById(R.id.dailyCheckCard);
            taskCard6=itemView.findViewById(R.id.taskCard6);
            datetarik=itemView.findViewById(R.id.datetarik);
            addkisti=itemView.findViewById(R.id.addkisti);
        }
    }
}


