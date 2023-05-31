package com.meass.calculator_apps;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NewspaperAdapter extends RecyclerView.Adapter<NewspaperAdapter.myview> {
    public List<NewspapeModel> data;
    FirebaseFirestore firebaseFirestore;

    public NewspaperAdapter(List<NewspapeModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public NewspaperAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newspapershome, parent, false);
        return new NewspaperAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewspaperAdapter.myview holder, final int position) {

holder.deposit__history.setText(data.get(position).getTitle());
holder.deposit__history.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        holder.scrollvieeeew.setVisibility(View.VISIBLE);
        holder.blog_detail_desc.setText(data.get(position).getShort_des());
    }
});
holder.scrollvieeeew.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        holder.scrollvieeeew.setVisibility(View.GONE);
    }
});

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        TextView deposit__history, blog_detail_desc, customer_area, logout;
        NestedScrollView scrollvieeeew;

        public myview(@NonNull View itemView) {
            super(itemView);
            deposit__history=itemView.findViewById(R.id.deposit__history);
            scrollvieeeew=itemView.findViewById(R.id.scrollvieeeew);
            blog_detail_desc=itemView.findViewById(R.id.blog_detail_desc);
        }
    }
}