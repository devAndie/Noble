package com.example.raven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logIn = (Button)findViewById(R.id.logInbtn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLogin = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(startLogin);
            }
        });
        Button signUp = (Button)findViewById(R.id.signUpbtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSignup = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(startSignup);
            }
        });
    }
}
