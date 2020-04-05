package com.culmyca.ec_2020_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.culmyca.ec_2020_app.adapter.DevelopersAdapter;
import com.culmyca.ec_2020_app.model.DeveloperModel;

import java.util.ArrayList;

public class developers extends Fragment {
   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.developers,container,false);
    }*/

    private ArrayList<DeveloperModel> dev = new ArrayList<>();
    private  RecyclerView recyclerView;
    DevelopersAdapter mAdapter;
    DeveloperModel developer;
    Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        mContext = context;
    }
    public developers() {
        // Required empty public constructor
    }
    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.developersdata_fragment, container, false );
        if (mContext == null) {
            mContext = getActivity();
        }
        recyclerView = (RecyclerView) view.findViewById( R.id.dev_recycler_view );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( mContext );
        recyclerView.setLayoutManager( mLayoutManager );
        Boolean k=true;
     //    AppCompatDelegate.setCompatVectorFromResourcesEnabled( true );
        developer=new DeveloperModel ("https://i.ibb.co/BCMsDbB/yash.jpg","Yash Dhingra","https://www.linkedin.com/in/yash-dhingra-5ab39a155/","Team Head","https://github.com/yashdhingra0");
        dev.add(developer);
        developer=new DeveloperModel ("https://i.ibb.co/zb5XZv7/IMG-20191005-WA0083-01.jpg","Priyanka Garg","https://www.linkedin.com/in/priyanka-garg-9b386215b","Co-ordinator","https://github.com/priyanka1698");
         dev.add(developer);

        developer=new DeveloperModel ("https://i.ibb.co/8dBkRWG/karan.jpg","Karan Bhati","https://www.linkedin.com/in/karan-bhati-162367137/","Core App Developer","https://github.com/karanbhati007");
        dev.add(developer);

        developer=new DeveloperModel ("https://i.ibb.co/931z4vr/rishabh.jpg","Rishabh Gupta","https://www.linkedin.com/in/rishabh-gupta-966701172/","Core App Developer","https://github.com/rishabh9720");
        dev.add(developer);

        developer=new DeveloperModel ("https://i.ibb.co/qYwHv4P/IMG-20191013-WA0008.jpg","Payal Mangla","https://www.linkedin.com/in/payal-mangla-b32792172","Core App Developer","https://github.com/payalmangla17");
        dev.add(developer);

        developer=new DeveloperModel ("https://i.ibb.co/WVdxqWN/me.jpg","Ridham Bhat","http://linkedin.com/in/ridham-bhat-49652417372","Core App Developer","http://github.com/ridhambhat");
        dev.add(developer);

        developer=new DeveloperModel ("https://i.ibb.co/4JsbzzR/profilepic.jpg","Manik Garg","https://www.linkedin.com/in/manik-garg-5060a4172","Core App Developer","https://github.com/Manik-Garg");
        dev.add(developer);



        mAdapter = new DevelopersAdapter( mContext,dev);

        recyclerView.setAdapter( mAdapter );
        return view;
    }



   }

