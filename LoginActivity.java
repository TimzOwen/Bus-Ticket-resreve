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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button btnLogin;
    private EditText etPass, etEmail;
    private TextView tvAlreadyRegistered;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog pProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser()!=null)
//        {
//            //user profile here
//            Intent intent = new Intent(LoginActivity.this, FindTripActivity.class);
//            startActivity(intent);
//        }

        pProgressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(this);
        tvAlreadyRegistered.setOnClickListener(this);

    }
    
    //function that finds the relevant ids of the views
    public void findIds()
    {
         etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPassword);
        tvAlreadyRegistered = (TextView) findViewById(R.id.tvAlready_registered);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View view)
    {
        if (view == btnLogin)
        {
            loginUser();
        }
        if (view == tvAlreadyRegistered)
        {
            startActivity(new Intent(this, SignUpActivity.class));
        }

    }

    private void loginUser()
    {
        //validate User
        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();

        if (!TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Enter Password", Toast.LENGTH_LONG).show();
        }
        if (!TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Enter Password", Toast.LENGTH_LONG).show();
        }


        pProgressDialog.setMessage("logging User.....");
        pProgressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        pProgressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            pProgressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, FindTripActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
