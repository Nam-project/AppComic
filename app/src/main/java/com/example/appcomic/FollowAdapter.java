package com.example.appcomic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FollowAdapter extends FirebaseRecyclerAdapter<TheoDoiModel, FollowAdapter.myViewholder> {


    public FollowAdapter(@NonNull FirebaseRecyclerOptions<TheoDoiModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull TheoDoiModel model) {
        holder.nameFollow.setText(model.getName());
        Glide.with(holder.imgFollow.getContext()).load(model.getImg()).into(holder.imgFollow);

        holder.btnDeleteF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.skey = "";
                holder.tmp = 0;
                DatabaseReference df = FirebaseDatabase.getInstance().getReference("TheoDoi");
                df.orderByChild("email").equalTo(HomeFragment.e).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot ds : snapshot.getChildren()) {
                            if (model.getKeyComic() == ds.child("keyComic").getValue(int.class)) {
                                holder.skey = ds.getKey();
                                holder.tmp +=1;
                            }
                        }
                        if (holder.tmp == 1) {
                            FirebaseDatabase.getInstance().getReference("TheoDoi/"+holder.skey).removeValue();
                        }

//                        FirebaseDatabase.getInstance().getReference("HistoryComic/"+key).removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_follow, parent, false);
        return new myViewholder(view);
    }

    public class myViewholder extends RecyclerView.ViewHolder {

        int tmp;
        String skey;
        ImageView imgFollow;
        TextView nameFollow;
        ImageButton btnDeleteF;

        public myViewholder(@NonNull View itemView) {
            super(itemView);
            imgFollow = itemView.findViewById(R.id.imgFollow);
            nameFollow = itemView.findViewById(R.id.nameFollow);
            btnDeleteF = itemView.findViewById(R.id.btnDeleteF);


        }
    }
}
