package com.example.ec_2020_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ec_2020_app.model.User;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Splash_screen extends AppCompatActivity {



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Objects.requireNonNull(getSupportActionBar()).hide();
        ImageView back = findViewById(R.id.splashh);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        back.startAnimation(myanim);

        final Intent i = new Intent(this, login.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);//will change it back
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {




                    startActivity(i);
                        finish();

                }
            }
        };
        timer.start();

    }
}
