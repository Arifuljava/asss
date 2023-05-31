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

public class DemoProductsAdapter extends RecyclerView.Adapter<DemoProductsAdapter.myview> {
    public List<Model1> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public DemoProductsAdapter(List<Model1> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.priductslist,parent,false);
        return new myview(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {

      holder.productsname.setText(data.get(position).getProductsname()+" = "+data.get(position).getPrice());





    }
    int flag=1;

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView productsname,customer_number,customer_area,logout,customer_area3,customer_area8;
        CardView card_view8;
        ImageView profile_image;
        LinearLayout idsecondlayout,firstlay;
        Button dailyCheckCard,taskCard6,bakikisti,datetarik,addkisti,namee,jomafirst;
        public myview(@NonNull View itemView) {
            super(itemView);
            productsname=itemView.findViewById(R.id.productsname);


        }
    }
}


