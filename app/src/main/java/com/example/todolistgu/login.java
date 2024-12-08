package com.example.todolistgu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
     DatabaseLogin databaseLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EditText username= findViewById(R.id.username2);

        EditText password= findViewById(R.id.password2);
        Button loginbtn  =  findViewById(R.id.loginbtn);
        databaseLogin = new DatabaseLogin(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = username.getText().toString().trim();
                String passwordText = password.getText().toString().trim(); // Corrected to get from password field

                if (!usernameText.isEmpty() && !passwordText.isEmpty()) {
                    // Check if username and password match in the database
                    boolean success = databaseLogin.userCheckDatabase(usernameText, passwordText);
                    if (success) {
                        // Navigate to MainActivity if login is successful
                        Intent intent = new Intent(login.this, MainActivity.class); // Ensure MainActivity is correct
                        startActivity(intent);
                        finish(); // Optionally finish the login activity to prevent returning to it
                    } else {
                        // Show error if username or password is incorrect
                        Toast.makeText(login.this, "Username or password is wrong", Toast.LENGTH_SHORT).show();
                    }
                } else if (usernameText.isEmpty()) {
                    // Show error for empty username
                    username.setError("Username must not be empty");
                    username.requestFocus();
                } else if (passwordText.isEmpty()) {
                    // Show error for empty password
                    password.setError("Password must not be empty");
                    password.requestFocus();
                }
            }
        });
    }
}
