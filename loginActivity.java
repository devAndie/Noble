package com.example.raven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginActivity extends AppCompatActivity {
    public static final int MY_PASSWORD_DIALOG_ID = 4;
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");

    private  EditText name;
    private EditText pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.nameTxt);
        pswd = findViewById(R.id.pwd);

        if(getIntent().hasExtra("com.examples.raven.name")){
            TextView user = findViewById(R.id.textView);
            String name = getIntent().getExtras().getString("com.examples.raven.name");
            user.setText(name);
        }


        Button logIn = (Button)findViewById(R.id.logInbtn2);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.nameTxt);
                EditText pswd = findViewById(R.id.pwd);

                String validUsername = "";
                String Username = name.getText().toString();
                Matcher matcher = Pattern.compile(validUsername).matcher(Username);
                if (matcher.matches()){
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter valid Username", Toast.LENGTH_LONG).show();
                }
                if (pswd.getText().toString().equals("")){
                    pswd.setError("Enter password");
                };

                Intent startIntent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivity(startIntent);
            }
        });

        Button cancel = (Button)findViewById(R.id.cancelbtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent backIntent = new Intent(getApplicationContext(), SignupActivity.class);
                    startActivity(backIntent);
                }
        });

    }

    private boolean validateUsername() {
        String nameInput = name.getEditableText().toString().trim();

        if (nameInput.isEmpty()) {
            name.setError("Field can't be empty");
            return false;
        }else if (nameInput.length() > 12) {
            name.setError("Username too long");
            return false;
        }else {
            name.setError(null);
            return  true;
        }
    }

    public boolean validatePassword() {
        String passwordInput = pswd.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            pswd.setError("Field can't be empty");
            return false;
        }else {
            pswd.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateUsername() | !validatePassword()) {
            return;
        }
        String input = "Username: " + name.getEditableText().toString().trim();
        input += "\n";
        input += "Password: " + pswd.getEditableText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }


}
