package com.meass.calculator_apps;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class AdapterSub7 extends RecyclerView.Adapter<AdapterSub7.myview> {
    public List<AddmemberModel> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public AdapterSub7(List<AddmemberModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sub2,parent,false);
        return new myview(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        String iamegs=data.get(position).getImage();
        String image="https://firebasestorage.googleapis.com/v0/b/cash-money-express-ltd.appspot.com/o/profile_images%2Fo8Dnqf5LFodKSwocGQ4nKB7ZEkW2.jpg?alt=media&token=c22700e2-67ca-4497-8bf1-204ac83b6749";
        if (image.equals(iamegs)) {

        }
        else {
            try {
                Picasso.get().load(data.get(position).getImage()).into(holder.profile_image);
            }catch (Exception e) {
                Picasso.get().load(data.get(position).getImage()).into(holder.profile_image);
            }

        }
        holder.customer_name.setText("সদস্যর নাম : "+data.get(position).getSovapoti()+"\nসমিতির নাম : "+data.get(position).getSomitiname());
        holder.customer_number.setText("লাস্ট কিস্তি");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        try {
            ZoneId z = ZoneId.of( "Asia/Dhaka" );
            LocalDate today = LocalDate.now( z );
            String dateee=data.get(position).getDate();
            Date date1 = dateFormat.parse(dateee);
            //4555545745
            Date date2 = dateFormat.parse(String.valueOf(today));
            long diff = date2.getTime() - date1.getTime();
            int time= (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            //Toast.makeText(holder.itemView.getContext(), ""+ TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS), Toast.LENGTH_SHORT).show();
            if (time>=7) {
                holder.logout.setText(data.get(position).getS_kisti()+" ট\nঅপরিশোধিত");
            }
            else {holder.logout.setText(data.get(position).getS_kisti()+" ট\nপরিশোধ");

            }
        } catch (ParseException e) {
            e.printStackTrace();
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
        firebaseFirestore.collection("KistiList")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .collection("List")
                .document(data.get(position).getSovapoti_english().toString().toLowerCase())
                .collection("List")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int ncoun=0;
                            for (DocumentSnapshot document : task.getResult()) {
                                ncoun++;
                            }
                            int baki=66-ncoun;
                           // int baki=Integer.parseInt(data.get(position).getRefer())-ncoun;
                            holder.bakikisti.setText(""+baki+" টি");

                        }
                    }
                });
        String date=data.get(position).getDate();
        ZoneId z = ZoneId.of( "America/Montreal" );
        LocalDate today = LocalDate.parse(date);
        LocalDate oneMonthLater = today.plusDays( 7 );
        holder.datetarik.setText(date+" - "+oneMonthLater);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        holder.namee.setText(data.get(position).getSovapoti());
        holder.jomafirst.setText(data.get(position).getPayment()+" টাকা");

holder.tottt.setText(data.get(position).getRefer()+"টি");



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
        Button dailyCheckCard,taskCard6,bakikisti,datetarik,addkisti,namee,jomafirst,tottt;
        public myview(@NonNull View itemView) {
            super(itemView);
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
            tottt=itemView.findViewById(R.id.tottt);

        }
    }
}


