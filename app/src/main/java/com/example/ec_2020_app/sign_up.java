package com.example.ec_2020_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ec_2020_app.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class sign_up extends AppCompatActivity {

    FirebaseAuth fauth;
    TextView loggin_page,skip_signup;
    Button signup_click;
    private static final String TAG = "siggnup";
    TextInputEditText t_name, t_college, t_email, t_mobile;
    String mobile;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    FirebaseDatabase mDatabase ;
    DatabaseReference signupRef;
    String verify_Id;
    PhoneAuthProvider.ForceResendingToken force_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        mDatabase = FirebaseDatabase.getInstance();
        final Intent i = new Intent(this, login.class);
        final Intent skipp = new Intent(this, MainActivity.class);

        signupRef = mDatabase.getReference("users");

        loggin_page = findViewById(R.id.open_login);
        skip_signup = findViewById(R.id.skip_signup);

            skip_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String datta = "rtr";
                    skipp.putExtra("mmobile",datta);
                    startActivity(skipp);
                }
            });

        loggin_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        t_name = findViewById(R.id.signup_name);
        t_college = findViewById(R.id.signup_college);
        t_email = findViewById(R.id.signup_email);
        t_mobile = findViewById(R.id.signup_number);

        signup_click = findViewById(R.id.btn_signUp);
        fauth = FirebaseAuth.getInstance();

        signupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.toString();
               // String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "sign up value read  is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        signup_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                registerMobile();
            }
        });
        fauth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

               // Log.e("uid new :",""+firebaseAuth.getUid());

            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Log.d(TAG, "onVerificationCompleted:" + credential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {



                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                }

                else if (e instanceof FirebaseTooManyRequestsException) {
                }

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                Log.d(TAG, "onCodeSent:" + verificationId);
                verify_Id=verificationId;
                force_token=token;
                Log.e("onCodeSent","onCodeSent");
                otpDialog();
            }
        };

    }



    private void saveDataInFirebase(String uid)
    {
        String name = t_name.getText().toString().trim();
        String email = t_email.getText().toString().trim();
        String college = t_college.getText().toString().trim();
        mobile = t_mobile.getText().toString().trim();

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(email) || TextUtils.isEmpty(college))
        {

            Toast.makeText(sign_up.this,"empty fields required",Toast.LENGTH_SHORT).show();
            return;
        }


        User user = new User(name,mobile,email,college);
       // String uid = signupRef.push().getKey();
        //  String uid = fauth.
        Log.e("uid : ",""+uid);
        uid=t_mobile.getText().toString();
        signupRef.child(uid).setValue(user);

    }
    EditText otp_text;
    private void otpDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(sign_up.this);

        View view = getLayoutInflater().inflate(R.layout.fragment_otp_checker,null);
        otp_text = view.findViewById(R.id.et_otp_dig_1);
        builder.setCancelable(false);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(otp_text.getText().toString().equals(""))
                {
                    Toast.makeText(sign_up.this, "Invalid otp", Toast.LENGTH_SHORT).show();
                    return ;
                }

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verify_Id, otp_text.getText().toString().trim());
                signInWithPhoneAuthCredential(credential);

            }
        });
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            Toast.makeText(sign_up.this,"success",Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();

                            saveDataInFirebase(user.getUid());

                            if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                            {
                                Toast.makeText(sign_up.this, "logged in", Toast.LENGTH_SHORT).show();
                            }

                            Intent inten = new Intent(sign_up.this,MainActivity.class);
                            inten.putExtra("mmobile",t_mobile.getText().toString().trim());
                            startActivity(inten);
                            // ...
                        } else {
                            Toast.makeText(sign_up.this,"failed",Toast.LENGTH_SHORT).show();

                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            }
                        }
                    }
                });
    }
String mob_no;
    private void registerMobile()
    {
        mob_no="+91"+t_mobile.getText();
        Log.e("registerMobile","mob_no length  :"+mob_no.length()+ " mob no: "+mob_no);
        if(mob_no.length()!=13)
        {
            Toast.makeText(this,"Enter a valid 10 digit number",Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this,"Please wait !!",Toast.LENGTH_SHORT).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mob_no,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }


}
