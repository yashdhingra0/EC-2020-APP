package com.example.ec_2020_app.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ec_2020_app.MainActivity;
import com.example.ec_2020_app.R;
import com.example.ec_2020_app.sign_up;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

import static com.example.ec_2020_app.Login.FragmentOtpChecker.REQUEST_ID_MULTIPLE_PERMISSIONS;

public class login extends Activity implements FragmentOtpChecker.otpCheckStatus{

    TextView guestLogin,signUp;
    EditText phoneNumber;
    private ProgressDialog mProgress;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumber = findViewById(R.id.num_text);
        submit = findViewById(R.id.login_button);
        signUp=findViewById(R.id.open_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(login.this, sign_up.class);
                startActivity(i);
            }
        });
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Registering You");
        mProgress.setTitle("Please Wait");
        mProgress.setCanceledOnTouchOutside(false);
        ///////////
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        String color = "#0f0f0f";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor(color));
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checker = validateCredentials();
                if (checker) {
                    mProgress.show();
                    checkOTP();
                }
            }
        });
        guestLogin = findViewById(R.id.skip_signin);

        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skip = new Intent(login.this, MainActivity.class);
                String data = "rtr";
                skip.putExtra("mmobile",data);
                finish();
                startActivity(skip);
            }
        });
    }

    private Boolean validateCredentials() {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phoneNumber.getText().toString().equals("")) {
            phoneNumber.setError("Enter a Phone Number");
            return false;
        }
        if (!Patterns.PHONE.matcher(phoneNumber.getText().toString()).matches()) {
            phoneNumber.setError("Enter a valid Phone Number");
            return false;
        }
        if (phoneNumber.getText().toString().length() != 10) {
            phoneNumber.setError("Enter a valid Phone Number");
            return false;
        }

        return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private void checkOTP() {
        checkAndRequestPermissions();
        if(ContextCompat.checkSelfPermission(login.this, android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){
            FragmentManager fm = getFragmentManager();
            FragmentOtpChecker otpChecker = new FragmentOtpChecker();
            Bundle bundle = new Bundle();
            bundle.putString("phone", phoneNumber.getText().toString());
            otpChecker.setArguments(bundle);
            otpChecker.show(fm, "otpCheckerFragment");
        }
        mProgress.dismiss();
    }
    private void checkAndRequestPermissions() {
        int receiveSMS = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.RECEIVE_SMS);

        int readSMS = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_SMS);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }
    @Override
    public void updateResult(boolean status) {
        if (status) {
            Intent intent = new Intent(login.this,MainActivity.class);
            //TODO
            //shared prefrece

            String datta = "rtr";
            intent.putExtra("mmobile",datta);
            finish();
            startActivity(intent);
            finish();
        } else {
            mProgress.dismiss();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS){

            FragmentManager fm = getFragmentManager();
            FragmentOtpChecker otpChecker = new FragmentOtpChecker();
            Bundle bundle = new Bundle();
            bundle.putString("phone", phoneNumber.getText().toString());
            otpChecker.setArguments(bundle);
            otpChecker.show(fm, "otpCheckerFragment");
        }
    }
}
