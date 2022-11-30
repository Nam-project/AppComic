package com.example.appcomic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class InformationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String name, image, tacGia, tomTat;

    public InformationFragment() {

    }
    public InformationFragment(String name, String image, String tacGia, String tomTat) {
        this.name = name;
        this.image = image;
        this.tacGia = tacGia;
        this.tomTat = tomTat;
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

        nameholder.setText(name);
        tacGiaholder.setText(tacGia);
        Glide.with(getContext()).load(image).into(imageViewholder);
        tomTatholder.setText(tomTat);

        return view;
    }

    public void onBackPerssed(){
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();

    }
}