package com.example.appcomic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public class ChapterAdapter extends FirebaseRecyclerAdapter<ChapterModel, ChapterAdapter.Chapterviewholder> {

    int i = 1;
    public ChapterAdapter(@NonNull FirebaseRecyclerOptions<ChapterModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Chapterviewholder holder, int position, @NonNull ChapterModel model) {
        holder.numberText.setText(String.valueOf(i));
        i++;
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChapterFragment(model.getKey(), model.getNoiDung(), InformationFragment.name)).addToBackStack(null).commit();

            }
        });

    }

    @NonNull
    @Override
    public Chapterviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chapter, parent, false);
        return new Chapterviewholder(view);
    }

    public class Chapterviewholder extends RecyclerView.ViewHolder {

        TextView numberText;
        LinearLayout layout;
        public Chapterviewholder(@NonNull View itemView) {
            super(itemView);

            numberText = itemView.findViewById(R.id.tvNumberChapter);
            layout = itemView.findViewById(R.id.idClickChapter);
        }
    }
}
