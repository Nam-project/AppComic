package com.example.appcomic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class InformationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public static String name;
    String image, tacGia, tomTat, theLoai;
    int key;
    RecyclerView recyclerView;
    ChapterAdapter adapter;

    public InformationFragment() {

    }

    public InformationFragment(String name, String image, String tacGia, String tomTat, String theLoai, int key) {
        this.name = name;
        this.image = image;
        this.tacGia = tacGia;
        this.tomTat = tomTat;
        this.theLoai = theLoai;
        this.key = key;
    }

    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        ImageView imageViewholder = view.findViewById(R.id.imgInfoC);
        TextView nameholder = view.findViewById(R.id.tvNameComicIF);
        TextView tacGiaholder = view.findViewById(R.id.tvTacGiaIF);
        TextView tomTatholder = view.findViewById(R.id.tvTomtatIF);
        TextView theLoaiholder = view.findViewById(R.id.tvTheLoaiIF);
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        recyclerView = view.findViewById(R.id.recyclerViewChapter);

        nameholder.setText(name);
        tacGiaholder.setText(tacGia);
        Glide.with(getContext()).load(image).into(imageViewholder);
        tomTatholder.setText(tomTat);
        theLoaiholder.setText(theLoai);

//        Toast.makeText(getContext(), ""+key, Toast.LENGTH_SHORT).show();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPerssed();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("ComicChapter").orderByChild("keyComic").equalTo(key);

        FirebaseRecyclerOptions<ChapterModel> options =
                new FirebaseRecyclerOptions.Builder<ChapterModel>()
                        .setQuery(query, ChapterModel.class)
                        .build();

        adapter = new ChapterAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void onBackPerssed() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();

    }
}