package com.example.todolistgu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
   String usernameText ;
   String passwordText;
   String emailText;
    DatabaseLogin databaseLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        EditText username= findViewById(R.id.username2);

        EditText email= findViewById(R.id.email);

        EditText password= findViewById(R.id.password);

        Button registerbtn  =  findViewById(R.id.regesterbtn);
        databaseLogin= new DatabaseLogin(this);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 passwordText= password.getText().toString();
                 emailText= email.getText().toString();
                 usernameText= username.getText().toString();
                if(!usernameText.isEmpty() && !emailText.isEmpty() && !passwordText.isEmpty()) {
                    databaseLogin.insertUser(usernameText,emailText  ,passwordText );
                    Intent intent = new Intent(register.this, login.class);
                    startActivity(intent);
                }
                else if(usernameText.isEmpty()) {
                    username.setError("username must not be empty");
                    username.requestFocus();
                }
                else if(emailText.isEmpty()) {
                    email.setError("username must not be empty");
                    email.requestFocus();
                }
                else if(passwordText.isEmpty()) {
                    password.setError("username must not be empty");
                    password.requestFocus();
                }
            }
        });



    }
}
