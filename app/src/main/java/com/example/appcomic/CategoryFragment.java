package com.example.appcomic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recycler;
    ComicAdapter adapterCD, adapterMHua, adapterDG;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;


    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        view = inflater.inflate(R.layout.fragment_category, container, false);
        viewManhua();
        viewCoDai();
        viewDiGioi();


        return view;
    }

    public void onStart() {
        super.onStart();
        adapterCD.startListening();
        adapterMHua.startListening();
        adapterDG.startListening();

    }
    @Override
    public void onStop() {
        super.onStop();
        adapterCD.stopListening();
        adapterMHua.stopListening();
        adapterDG.stopListening();

    }

    public void viewManhua(){
        recycler = view.findViewById(R.id.recyclerManhua);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycler.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<ComicModel> options =
                new FirebaseRecyclerOptions.Builder<ComicModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Comic").orderByChild("theLoai").equalTo("Manhua").limitToFirst(4), ComicModel.class)
                        .build();

        adapterMHua = new ComicAdapter(options);
        recycler.setAdapter(adapterMHua);
    }
    public void viewDiGioi(){
        recycler = view.findViewById(R.id.recyclerDiGioi);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycler.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<ComicModel> options =
                new FirebaseRecyclerOptions.Builder<ComicModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Comic").orderByChild("theLoai").equalTo("Dị Giới").limitToFirst(4), ComicModel.class)
                        .build();

        adapterDG = new ComicAdapter(options);
        recycler.setAdapter(adapterDG);
    }
    public void viewCoDai(){
        recycler = view.findViewById(R.id.recyclerCoDai);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycler.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<ComicModel> options =
                new FirebaseRecyclerOptions.Builder<ComicModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Comic").orderByChild("theLoai").equalTo("Cổ đại").limitToFirst(4), ComicModel.class)
                        .build();

        adapterCD = new ComicAdapter(options);
        recycler.setAdapter(adapterCD);
    }
}