package com.example.pillremainder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pillremainder.includes.MyToolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;


public class Start_20_Login extends AppCompatActivity {

//    Toolbar toolbar_Login;
    TextInputEditText myTextInputEditEmail;
    TextInputEditText myTextInputEditPass;
    Button myBtnLogin2;

    FirebaseAuth myAuth;
    DatabaseReference myDatabase;

    AlertDialog myDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start20_login);

        MyToolbar.show(this,"Login", true);
//        toolbar_Login = findViewById(R.id.toolbarLogin);
//        setSupportActionBar(toolbar_Login);
//        getSupportActionBar().setTitle("Log In");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myTextInputEditEmail = findViewById(R.id.textInputEmail);
        myTextInputEditPass = findViewById(R.id.textInputPass);
        myBtnLogin2 = findViewById(R.id.btnLogin2);

        myAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance().getReference();

        myDialog = new SpotsDialog.Builder().setContext(Start_20_Login.this).setMessage("Please Wait...").build();


        myBtnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String email = myTextInputEditEmail.getText().toString();
        String password = myTextInputEditPass.getText().toString();

        if(!email.isEmpty() && !password.isEmpty())
        {
            if (password.length() >= 6)
            {
                myDialog.show();
                myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Start_20_Login.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                            goToHome();
                        }
                        else {
                            Toast.makeText(Start_20_Login.this, "email or password are not correct", Toast.LENGTH_SHORT).show();
                        }
                        myDialog.dismiss();
                    }
                });
            }
            else {
                Toast.makeText(Start_20_Login.this, "Password needs 6 characters", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(Start_20_Login.this, "Email and password are required", Toast.LENGTH_SHORT).show();
        }
    }



    private void goToHome() {
        Intent intent = new Intent(Start_20_Login.this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}