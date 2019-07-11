package com.owen.bussystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener
{

    private Button btnHomeLoggin, btnHomeSignUp;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser()!=null)
//        {
//            //user profile here
//            Intent intent = new Intent(MainActivity.this, FindTripActivity.class);
//            startActivity(intent);
//        }

        btnHomeLoggin = (Button) findViewById(R.id.btn_hmeLogin);
        btnHomeSignUp = (Button) findViewById(R.id.btn_hmeSignUp);

        btnHomeSignUp.setOnClickListener(this);
        btnHomeLoggin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if (view == btnHomeLoggin)
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        if (view == btnHomeSignUp)
        {
            Intent intent = new Intent(MainActivity.this , SignUpActivity.class);
            startActivity(intent);
        }

    }
}
