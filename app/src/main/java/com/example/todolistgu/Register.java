package com.example.todolistgu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
   String usernameText ;
   String passwordText;
   String emailText;
   Button haveAccount;

    DatabaseLogin databaseLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        EditText username= findViewById(R.id.username);

        EditText email= findViewById(R.id.email);

        EditText password= findViewById(R.id.password);
        haveAccount = findViewById(R.id.haveAcount);
        Button registerbtn  =  findViewById(R.id.registerbtn);
        databaseLogin= new DatabaseLogin(this);

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, login.class);
                startActivity(intent);
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordText = password.getText().toString().trim();
                emailText = email.getText().toString().trim();
                usernameText = username.getText().toString().trim();

                // Validate that the fields are not empty
                if (!usernameText.isEmpty() && !emailText.isEmpty() && !passwordText.isEmpty()) {
                    // Insert the user into the database
                    boolean isInserted = databaseLogin.insertUser(usernameText, emailText, passwordText);

                    // If insertion is successful, navigate to login screen
                    if (isInserted) {
                        Intent intent = new Intent(Register.this, login.class); // Ensure Login.class is correct
                        startActivity(intent);
                    } else {
                        // Show error message if insertion failed
                        Toast.makeText(Register.this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                    }

                } else if (usernameText.isEmpty()) {
                    // Show error for empty username
                    username.setError("Username must not be empty");
                    username.requestFocus();
                } else if (emailText.isEmpty()) {
                    // Show error for empty email
                    email.setError("Email must not be empty");
                    email.requestFocus();
                } else if (passwordText.isEmpty()) {
                    // Show error for empty password
                    password.setError("Password must not be empty");
                    password.requestFocus();
                }
            }
        });



    }
}
