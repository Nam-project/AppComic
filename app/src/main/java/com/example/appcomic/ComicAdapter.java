package com.example.appcomic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.tvimgComic);
            name = itemView.findViewById(R.id.tvnameComic);


        }
    }
}
