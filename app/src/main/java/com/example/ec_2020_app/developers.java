package com.example.ec_2020_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ec_2020_app.adapter.DevelopersAdapter;
import com.example.ec_2020_app.model.DeveloperModel;

import java.util.ArrayList;

public class developers extends Fragment {
   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.developers,container,false);
    }*/

    private ArrayList<DeveloperModel> dev = new ArrayList<>();
    RecyclerView recyclerView;
    DevelopersAdapter mAdapter;
    DeveloperModel developer;
    Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        mContext = context;
    }

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.developersdata_fragment, container, false );

        AppCompatDelegate.setCompatVectorFromResourcesEnabled( true );
        developer=new DeveloperModel ("R.drawable.yash","Yash Dhingra","https://www.linkedin.com/in/yash-dhingra-5ab39a155/","Team Head","https://github.com/yashdhingra0");
        dev.add(developer);
        developer=new DeveloperModel ("R.drawable.priyanka","Priyanka Garg","https://www.linkedin.com/in/priyanka-garg-9b386215b","Co-ordinator","https://github.com/priyanka1698");
         dev.add(developer);

        developer=new DeveloperModel ("R.drawable.karan","Karan Bhati","https://www.linkedin.com/in/karan-bhati-162367137/","Core App Developer","https://github.com/karanbhati007");
        dev.add(developer);

        developer=new DeveloperModel ("R.drawable.rishabh","Rishabh Gupta","https://www.linkedin.com/in/rishabh-gupta-966701172/","Core App Developer","https://github.com/rishabh9720");
        dev.add(developer);

        developer=new DeveloperModel ("R.drawable.payal","Payal Mangla","https://www.linkedin.com/in/payal-mangla-b32792172","Core App Developer","https://github.com/payalmangla17");
        dev.add(developer);

        developer=new DeveloperModel ("R.drawable.ridham","Ridham Bhat","http://linkedin.com/in/ridham-bhat-49652417372","Core App Developer","http://github.com/ridhambhat");
        dev.add(developer);

        developer=new DeveloperModel ("R.drawable.manik","Manik Garg","https://www.linkedin.com/in/manik-garg-5060a4172","Core App Developer","https://github.com/Manik-Garg");
        dev.add(developer);



        view.findViewById(R.id.dev_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment f =  fragmentManager.findFragmentById(R.id.frame);


                   fragmentManager.beginTransaction().replace(R.id.frame, new homepage()).commit();
                }


            }
        );
        recyclerView = (RecyclerView) view.findViewById( R.id.dev_recycler_view );
        mAdapter = new DevelopersAdapter( mContext,dev);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( mContext );
        recyclerView.setLayoutManager( mLayoutManager );
        recyclerView.setAdapter( mAdapter );
        return view;
    }

    private void addData() {


    }


   }

