package com.example.ec_2020_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.ec_2020_app.Login.login;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class nav_draw extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nav_main, container, false);
        NavigationView nav_view = v.findViewById(R.id.nav_view);

        DrawerLayout drawerLayout = v.findViewById(R.id.drawerLayout);
        drawerLayout.openDrawer(Gravity.LEFT);


        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_item_one:
                        //Toast.makeText(getActivity(), "ab", Toast.LENGTH_SHORT).show();
                     //  Intent i = new Intent(getContext(), homepage.class);
                     //  startActivity(i);
                     //   mview();
                       Fragment f=new homepage();
                       getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                        break;

                    case R.id.nav_item_two:
                        Intent it = new Intent(getActivity(), about.class);
                        startActivity(it);
                          break;


                    case R.id.nav_item_five:
                       // Toast.makeText(getActivity(), "ab", Toast.LENGTH_SHORT).show();
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String shareMessage= "EC-2020 APP\n\n";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch(Exception e) {
                            //e.toString();
                        }
                        break;
                    case R.id.nav_item_six:

                            logout();
                        break;
                                  }
                return true;
            }
        });

    return v;
    }

    private void logout() {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getActivity(), "Logging Out !!", Toast.LENGTH_SHORT).show();
            Intent go_to_login = new Intent(getActivity(), login.class);
            startActivity(go_to_login);
        }
        else Toast.makeText(getActivity(),"You are not logged in.",Toast.LENGTH_SHORT).show();
    }
    private void mview(){
        Intent intent=new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }

}


