package com.meass.calculator_apps;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NoteAdapterMemo extends RecyclerView.Adapter<NoteAdapterMemo.myView> {
    private List<MemoNoteModel> data;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public NoteAdapterMemo(List<MemoNoteModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbox, parent, false);
        return new myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myView holder, final int position) {
        if (data.get(position).getExpered().equals("মেয়াদোত্তীর্ণ  পণ্য")) {
            holder.add_notes_title.setText(data.get(position).getExpered());
            holder.add_notes_title.setTextColor(Color.RED);
            holder.blog_detail_desc.setText(data.get(position).getProductsname()+" : "+data.get(position).getPices()+" টি\n\n\n" +
                    ""+data.get(position).getDetails());
        }
        else {
            holder.add_notes_title.setText(data.get(position).getExpered());
            holder.add_notes_title.setTextColor(Color.GREEN);
            holder.blog_detail_desc.setText(data.get(position).getProductsname()+" : "+data.get(position).getPices()+" টি\n\n\n" +
                    ""+data.get(position).getDetails());
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myView extends RecyclerView.ViewHolder {
        TextView blog_detail_desc,add_notes_title;
        CardView cardsddd;
        public myView(@NonNull View itemView) {

            super(itemView);
            blog_detail_desc=itemView.findViewById(R.id.blog_detail_desc);
            add_notes_title=itemView.findViewById(R.id.add_notes_title);
            cardsddd=itemView.findViewById(R.id.cardsddd);

        }
    }
}
