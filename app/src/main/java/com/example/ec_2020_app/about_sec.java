package com.example.ec_2020_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_2020_app.adapter.DevelopersAdapter;
import com.example.ec_2020_app.model.DeveloperModel;

import java.util.ArrayList;

public class about_sec extends Fragment {
   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.developers,container,false);
    }*/


    Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        mContext = context;
    }
    public about_sec() {
        // Required empty public constructor
    }
    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.about, container, false );
        if (mContext == null) {
            mContext = getActivity();
        }

            return view;
    }



   }

