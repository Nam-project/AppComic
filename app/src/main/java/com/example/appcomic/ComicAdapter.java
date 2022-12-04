package com.example.appcomic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComicAdapter extends FirebaseRecyclerAdapter<ComicModel, ComicAdapter.viewholder> {

    public ComicAdapter(@NonNull FirebaseRecyclerOptions<ComicModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull ComicModel model) {
        holder.name.setText(model.getName());
        Glide.with(holder.imageView.getContext()).load(model.getImage()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                historyModel map = new historyModel();
                map.setName(model.getName());
                map.setImage(model.getImage());
                map.setTacGia(model.getTacGia());
                map.setKey(model.getKey());
                map.setTheLoai(model.getTheLoai());
                map.setTomTat(model.getTomTat());
                map.setEmail(HomeFragment.e);
                holder.tmp = 0;
                DatabaseReference df = FirebaseDatabase.getInstance().getReference("HistoryComic");
                df.orderByChild("email").equalTo(HomeFragment.e).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        
                        for(DataSnapshot ds : snapshot.getChildren()){
                            int keyH  = ds.child("key").getValue(int.class);
                            if (keyH == model.getKey()){
                                holder.tmp = holder.tmp + 1;
                            }
                        }
                        if (holder.tmp == 0) {
                            firebaseDatabase.getReference().child("HistoryComic").push().setValue(map);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new InformationFragment(model.getName(), model.getImage(), model.getTacGia(), model.getTomTat(), model.getTheLoai(), model.getKey())).addToBackStack(null).commit();

            }
        });

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comic,parent,false);
        return new viewholder(view);
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        int tmp;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.tvimgComic);
            name = itemView.findViewById(R.id.tvnameComic);


        }
    }
}
