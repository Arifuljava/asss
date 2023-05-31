package com.meass.calculator_apps;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PackageInAdapter extends RecyclerView.Adapter<PackageInAdapter.myview> {
    public List<OnlineserModel> data;
    FirebaseFirestore firebaseFirestore;

    public PackageInAdapter(List<OnlineserModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PackageInAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.papa, parent, false);
        return new PackageInAdapter.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageInAdapter.myview holder, final int position) {

holder.deposit__history.setText(data.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder {
        TextView deposit__history, customer_number, customer_area, logout;

        public myview(@NonNull View itemView) {
            super(itemView);
            deposit__history=itemView.findViewById(R.id.deposit__history);
        }
    }
}