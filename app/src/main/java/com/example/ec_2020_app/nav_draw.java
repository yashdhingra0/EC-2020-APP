package com.example.ec_2020_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class nav_draw extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nav_main, container, false);
        NavigationView nav_view = v.findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_item_one:
                       Toast.makeText(getActivity(), "ab", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.nav_item_two:
                        Intent i = new Intent(getActivity(), about.class);
                        startActivity(i);

                        break;


                    case R.id.nav_item_five:
                        Toast.makeText(getActivity(), "ab", Toast.LENGTH_SHORT).show();

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

}


