package com.example.appcomic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

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
    DatabaseReference databaseReference;

    String email;

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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
        ImageView imageViewholder = view.findViewById(R.id.imgInfoC);
        TextView nameholder = view.findViewById(R.id.tvNameComicIF);
        TextView tacGiaholder = view.findViewById(R.id.tvTacGiaIF);
        TextView tomTatholder = view.findViewById(R.id.tvTomtatIF);
        TextView theLoaiholder = view.findViewById(R.id.tvTheLoaiIF);
        ImageButton btnBack = view.findViewById(R.id.btnBack);
        Button btnTheoDoi = view.findViewById(R.id.btnTheoDoi);
        recyclerView = view.findViewById(R.id.recyclerViewChapter);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TheoDoi");

        DatabaseReference df = FirebaseDatabase.getInstance().getReference("TheoDoi");
        df.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    int keyC  = ds.child("keyComic").getValue(int.class);
                    if (keyC == key) {
                        btnTheoDoi.setText("Bỏ theo dõi");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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



        btnTheoDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnTheoDoi.getText().toString().equals("Theo dõi")) {
                    insertTheoDoi();
                    df.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            btnTheoDoi.setText("Theo dõi");
                            for(DataSnapshot ds : snapshot.getChildren()){
                                int keyC  = ds.child("keyComic").getValue(int.class);
                                if (keyC == key) {
                                    btnTheoDoi.setText("Bỏ theo dõi");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else {
                    DatabaseReference dl = FirebaseDatabase.getInstance().getReference("TheoDoi");
                    dl.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds : snapshot.getChildren()){
                                int keyC  = ds.child("keyComic").getValue(int.class);
                                if (keyC == key) {
                                    DatabaseReference dl1 = FirebaseDatabase.getInstance().getReference("TheoDoi/"+ds.getKey());
                                    dl1.removeValue();
                                    df.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            btnTheoDoi.setText("Theo dõi");
                                            for(DataSnapshot ds : snapshot.getChildren()){
                                                int keyC  = ds.child("keyComic").getValue(int.class);
                                                if (keyC == key) {
                                                    btnTheoDoi.setText("Bỏ theo dõi");
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
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

    private void insertTheoDoi() {
        TheoDoiModel theoDoi = new TheoDoiModel(email, key);
        databaseReference.push().setValue(theoDoi);
    }

    public void deleteTheoDoi() {

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