package com.example.raven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
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


    private EditText Phone;
    private EditText name;
    private EditText pswd;
    private EditText Confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Phone = findViewById(R.id.num2);
        name = findViewById(R.id.name2);
        pswd = findViewById(R.id.pwd2);
        Confirm = findViewById(R.id.pwd3);

        Button create = (Button)findViewById(R.id.okbtn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneNo = (EditText)findViewById(R.id.num2);
                EditText username = (EditText)findViewById(R.id.name2);
                EditText Password = (EditText)findViewById(R.id.pwd2);
                EditText confirmPassword = (EditText)findViewById(R.id.pwd3);

                String validUsername = "";
                String Username = username.getText().toString();
                Matcher matcher = Pattern.compile(validUsername).matcher(Username);
                if (matcher.matches()){
                    Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter valid Username", Toast.LENGTH_LONG).show();
                }
                if (confirmPassword.getText().toString().equals("")){
                        confirmPassword.setError("Enter password");
                };

                Intent startIntent = new Intent(getApplicationContext(), loginActivity.class);
                startActivity(startIntent);
            }
        });

        Button home = (Button)findViewById(R.id.backbtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                homeIntent.putExtra("com.examples.raven.name", getComponentName());
                startActivity(homeIntent);
            }
        });
    }

    public boolean validatePhonenumber(){
        String passwordInput = Phone.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            Phone.setError("Field can't be empty");
            return false;
        }else {
            Phone.setError(null);
            return true;
        }
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
        if (!validatePhonenumber() | !validateUsername() | !validatePassword()) {
            return;
        }
        String input = "Username: " + name.getEditableText().toString().trim();
        input += "\n";
        input += "Password: " + pswd.getEditableText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
