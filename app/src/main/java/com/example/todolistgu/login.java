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
                String usernameText= username.getText().toString();
                String passwordText= username.getText().toString();

                if(!usernameText.isEmpty() && !passwordText.isEmpty())
                {
                   boolean success = databaseLogin.userCheckDatabase(usernameText, passwordText);
                   if(success) {
                       Intent intent = new Intent(login.this, MainAmyctivity.class);
                       startActivity(intent);
                   }
                   else
                       Toast.makeText(login.this, "username or password is Wrong", Toast.LENGTH_SHORT).show();
                }
                else if(usernameText.isEmpty()) {
                    username.setError("username must not be empty");
                    username.requestFocus();
                }
                else if(passwordText.isEmpty()) {
                    password.setError("username must not be empty");
                    password.requestFocus();
                }
            }
        });
    }
}
