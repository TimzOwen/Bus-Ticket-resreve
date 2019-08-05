package com.owen.bussystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText etUemail, etUpassword;

    private Button btnSignUp;

    private TextView tvAlreadyRegistered;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         progressDialog = new ProgressDialog(this);

         firebaseAuth = FirebaseAuth.getInstance();

       if (firebaseAuth.getCurrentUser()!=null)
       {
           //user profile here
           Intent intent = new Intent(SignUpActivity.this, FindTripActivity.class);
           startActivity(intent);
       }

        collectUserIds();

        //set on click listner action to the btn and tv for action
        btnSignUp.setOnClickListener(this);
        tvAlreadyRegistered.setOnClickListener(this);


    }

    //function to collect Ids
    public void collectUserIds()
    {
        etUemail = (EditText) findViewById(R.id.etEmail);
        etUpassword  = (EditText) findViewById(R.id.etPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        tvAlreadyRegistered = (TextView) findViewById(R.id.tvAlready_registered);


    }

    //onClick function implemented
    @Override
    public void onClick(View view)
    {
        //check onwhiich view has been clicked
        if (view == btnSignUp)
        {
            registerUser();
        }
        if (view == tvAlreadyRegistered)
        {
            loginUser();
        }

    }

    public void registerUser()
    {
        String email = etUemail.getText().toString().trim();
        String password = etUpassword.getText().toString().trim();
        
        email.focuc();

        if (!TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Enter Password", Toast.LENGTH_LONG).show();
        }
        if (!TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter Password", Toast.LENGTH_LONG).show();
        }


        progressDialog.setMessage("Registering User.....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this, "User Registerted successfuly" ,Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                                //user profile here
                                  startActivity( new Intent(SignUpActivity.this, LoginActivity.class));
                               
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this, "Please try Again", Toast.LENGTH_SHORT).show();
                        }

                    }


                });
    }

    public void loginUser()
    {
        //login user
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }
}
