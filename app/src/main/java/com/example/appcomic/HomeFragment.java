package com.example.appcomic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    //    LinearLayoutManager mLinearLayoutManager;
    RecyclerView recyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    //    FirebaseRecyclerAdapter<ComicModel, ViewHolder> firebaseRecyclerAdapter;
//    FirebaseRecyclerOptions<ComicModel> options;
    ComicAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static String e;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //Anh xa
        ImageButton btnSearch = view.findViewById(R.id.btnSearch);
        ImageButton btnFollow = view.findViewById(R.id.btnFollow);
        ImageButton btnCotegory = view.findViewById(R.id.btnCotegory);
        ImageView btnHistory = view.findViewById(R.id.btnHistory);

        recyclerView = view.findViewById(R.id.recyclerViewComic);




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        e = user.getEmail();


        //Slider
        ImageCarousel carousel = view.findViewById(R.id.carousel);
        ArrayList<CarouselItem> clist = new ArrayList<>();
        clist.add(new CarouselItem(
                "https://st.ntcdntempv3.com/data/comics/182/truong-hoc-sieu-anh-hung.jpg",
                "My Hero Academia"
        ));
        clist.add(new CarouselItem(
                "https://st.ntcdntempv3.com/data/comics/69/black-clover-phap-su-khong-phep-thuat.jpg",
                "Black clover"
        ));
        clist.add(new CarouselItem(
                "https://st.ntcdntempv3.com/data/comics/162/gia-dinh-diep-vien.jpg",
                "Gia đình điệp viên"
        ));

        carousel.setData(clist);

        //End slider

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).addToBackStack(null).commit();

            }
        });

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new FollowFragment()).addToBackStack(null).commit();

            }
        });

        btnCotegory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new CategoryFragment()).addToBackStack(null).commit();

            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HistoryFragment()).addToBackStack(null).commit();

            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<ComicModel> options =
                new FirebaseRecyclerOptions.Builder<ComicModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Comic"), ComicModel.class)
                        .build();

        adapter = new ComicAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}